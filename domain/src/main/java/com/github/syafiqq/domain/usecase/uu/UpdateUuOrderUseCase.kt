package com.github.syafiqq.domain.usecase.uu

import com.github.syafiqq.domain.contract.repository.UuRepositoryInterface
import javax.inject.Inject

interface UpdateUuOrderUseCase {
    suspend fun execute()
}

class UpdateUuOrderUseCaseImpl @Inject constructor(
    val uuRepository: UuRepositoryInterface,
) : UpdateUuOrderUseCase {
    override suspend fun execute() {
        val order = uuRepository.fetchRemoteUuOrder()
        uuRepository.storeUuOrder(order)
    }
}
