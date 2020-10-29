package com.github.syafiqq.domain.usecase.uu

import com.github.syafiqq.domain.contract.repository.UuRepositoryInterface
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionEntity
import com.github.syafiqq.domain.util.UuYearListCollector
import javax.inject.Inject

interface UpdateUuRepositoryUseCase {
    suspend fun execute(to: RepositoryVersionEntity)
}

class UpdateUuRepositoryUseCaseImpl @Inject constructor(
    val uuRepository: UuRepositoryInterface,
    val deleteExistingUuRepositoryUseCase: DeleteExistingUuRepositoryUseCase,
) : UpdateUuRepositoryUseCase {
    private val collector = UuYearListCollector()
    override suspend fun execute(to: RepositoryVersionEntity) {
        deleteExistingUuRepositoryUseCase.execute()

        for (filename in to.filenames) {
            val uu = uuRepository.fetchRemoteUu(filename)
            collector.collect(uu)
            uuRepository.storeUu(uu)
        }
        uuRepository.storeUuYear(collector.getCollector)
    }
}
