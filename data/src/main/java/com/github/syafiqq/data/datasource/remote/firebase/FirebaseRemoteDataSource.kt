package com.github.syafiqq.data.datasource.remote.firebase

import com.github.syafiqq.data.datasource.remote.AppProfileRemoteDataSource
import com.github.syafiqq.data.datasource.remote.UuRemoteDataSource
import com.github.syafiqq.data.datasource.remote.entity.RepositoryVersionEntity
import com.github.syafiqq.data.datasource.remote.entity.UuEntity
import com.github.syafiqq.data.datasource.remote.entity.toDomain
import com.github.syafiqq.data.datasource.remote.firebase.util.FirebaseConstants
import com.github.syafiqq.data.datasource.remote.firebase.util.error.NoDataErrorException
import com.github.syafiqq.data.datasource.remote.firebase.util.error.ParseDataException
import com.github.syafiqq.domain.entity.uu.UuOrderEntity
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
import java.nio.charset.Charset
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionEntity as DomainRepositoryVersionEntity
import com.github.syafiqq.domain.entity.uu.UuEntity as DomainUuEntity

class FirebaseRemoteDataSource @Inject constructor() : AppProfileRemoteDataSource,
    UuRemoteDataSource {
    private fun versionQuery(): Query {
        return Firebase
            .database
            .reference
            .child(FirebaseConstants.PATHS.DB.VERSION)
            .child(FirebaseConstants.REPOSITORY_VERSION)
            .orderByKey()
            .limitToLast(1)
    }

    override suspend fun fetchVersion(): DomainRepositoryVersionEntity {
        return suspendCoroutine { continuation ->
            versionQuery()
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        continuation.resumeWithException(error.toException())
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.childrenCount > 0) {
                            val version = snapshot.children
                                .iterator()
                                .next()
                                .getValue(RepositoryVersionEntity::class.java)
                                ?.toDomain()
                            if (version == null) {
                                continuation.resumeWithException(NoDataErrorException())
                            } else {
                                version.let(continuation::resume)
                            }
                            return
                        }
                        continuation.resumeWithException(NoDataErrorException())
                    }
                })
        }
    }

    private fun orderQuery(): Query {
        return Firebase
            .database
            .reference
            .child(FirebaseConstants.PATHS.DB.ORDER)
    }

    override suspend fun fetchUuOrder(): List<UuOrderEntity> {
        return suspendCoroutine { continuation ->
            orderQuery()
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        continuation.resumeWithException(error.toException())
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

    private fun getUuQuery(filename: String): StorageReference {
        return Firebase
            .storage
            .reference
            .child(String.format("%s/%s", FirebaseConstants.PATHS.STORAGE.UU, filename))
    }

    override suspend fun fetchUu(filename: String): List<DomainUuEntity> {
        return suspendCoroutine { continuation ->
            getUuQuery(filename = filename)
                .getBytes(Long.MAX_VALUE).addOnSuccessListener {
                    val uu = it?.toListUUEntity()
                    if (uu == null) {
                        continuation.resumeWithException(NoDataErrorException())
                    } else {
                        continuation.resume(uu)
                    }
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }
}

private fun ByteArray.toListUUEntity(): List<DomainUuEntity> {
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(List::class.java, UuEntity::class.java)
    val jsonAdapter: JsonAdapter<List<UuEntity>> =
        moshi.adapter(type)

    return jsonAdapter
        .fromJson(this.toString(Charset.defaultCharset()))
        ?.map(UuEntity::toDomain)
        ?: throw ParseDataException()
}
