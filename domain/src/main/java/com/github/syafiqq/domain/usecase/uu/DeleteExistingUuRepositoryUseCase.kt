package com.github.syafiqq.domain.usecase.uu

import com.github.syafiqq.domain.contract.repository.UuRepositoryInterface
import javax.inject.Inject

interface DeleteExistingUuRepositoryUseCase {
    suspend fun execute()
}

class DeleteExistingRepositoryUseCaseImpl @Inject constructor(
    val uuRepository: UuRepositoryInterface
) : DeleteExistingUuRepositoryUseCase {
    override suspend fun execute() {
        uuRepository.removeAllUu()
        uuRepository.removeAllUuYear()
    }
}
