package com.github.syafiqq.data.datasource.remote.firebase.implementation

import com.github.syafiqq.common.error.ApiException
import com.github.syafiqq.data.datasource.remote.firebase.contract.AppProfileRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.contract.UuRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.entity.RepositoryVersionEntity
import com.github.syafiqq.data.datasource.remote.firebase.entity.UuEntity
import com.github.syafiqq.data.datasource.remote.firebase.entity.UuOrderEntity
import com.github.syafiqq.data.datasource.remote.firebase.util.FirebaseConstants
import com.github.syafiqq.data.datasource.remote.firebase.util.error.FirebaseExceptionFactory
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.coroutineScope
import java.nio.charset.Charset
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseRemoteDataSource @Inject constructor() : AppProfileRemoteDataSource,
    UuRemoteDataSource {

    override suspend fun fetchVersion(): RepositoryVersionEntity {
        return coroutineScope {
            suspendCoroutine { continuation ->
                versionQuery()
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(error: DatabaseError) {
                            continuation.resumeWithException(
                                FirebaseExceptionFactory.createFirebaseError(
                                    error.code,
                                    cause = error.toException()
                                )
                            )
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            val version = snapshot.children
                                .first()
                                ?.getValue(RepositoryVersionEntity::class.java)
                            if (version == null) {
                                continuation.resumeWithException(
                                    FirebaseExceptionFactory.createNoDataException()
                                )
                            } else {
                                continuation.resume(version)
                            }
                        }
                    })
            }
        }
    }

    override suspend fun fetchUuOrder(): List<UuOrderEntity> {
        return coroutineScope {
            suspendCoroutine { continuation ->
                orderQuery()
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(error: DatabaseError) {
                            continuation.resumeWithException(
                                FirebaseExceptionFactory.createFirebaseError(
                                    error.code,
                                    cause = error.toException()
                                )
                            )
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            val orders = mutableListOf<UuOrderEntity>()
                            for (childrenSnapshot in snapshot.children) {
                                childrenSnapshot.getValue(UuOrderEntity::class.java)
                                    ?.let(orders::add)
                            }
                            orders.sortedBy(UuOrderEntity::order)
                            continuation.resume(orders)
                        }
                    })
            }
        }
    }

    override suspend fun fetchUu(filename: String): List<UuEntity> {
        return coroutineScope {
            suspendCoroutine { continuation ->
                getUuQuery(filename = filename)
                    .getBytes(Long.MAX_VALUE)
                    .addOnSuccessListener {
                        try {
                            val uu = it?.toListUUEntity()
                            if (uu == null) {
                                continuation.resumeWithException(
                                    FirebaseExceptionFactory.createNoDataException()
                                )
                            } else {
                                continuation.resume(uu)
                            }
                        } catch (e: Exception) {
                            continuation.resumeWithException(e)
                        }
                    }
                    .addOnFailureListener {
                        continuation.resumeWithException(
                            FirebaseExceptionFactory.createFirebaseError(
                                0,
                                cause = it
                            )
                        )
                    }
            }
        }
    }

    private fun versionQuery(): Query {
        return Firebase
            .database
            .reference
            .child(FirebaseConstants.PATHS.DB.VERSION)
            .child(FirebaseConstants.REPOSITORY_VERSION)
            .orderByKey()
            .limitToLast(1)
    }

    private fun orderQuery(): Query {
        return Firebase
            .database
            .reference
            .child(FirebaseConstants.PATHS.DB.ORDER)
    }

    private fun getUuQuery(filename: String): StorageReference {
        return Firebase
            .storage
            .reference
            .child(String.format("%s/%s", FirebaseConstants.PATHS.STORAGE.UU, filename))
    }
}

@Throws(ApiException::class)
private fun ByteArray.toListUUEntity(): List<UuEntity> {
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(List::class.java, UuEntity::class.java)
    val jsonAdapter: JsonAdapter<List<UuEntity>> =
        moshi.adapter(type)

    return try {
        jsonAdapter
            .fromJson(this.toString(Charset.defaultCharset()))
            ?: throw FirebaseExceptionFactory.createParseDataException()
    } catch (e: Exception) {
        throw FirebaseExceptionFactory.createParseDataException(e)
    }
}
