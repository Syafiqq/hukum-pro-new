package com.github.syafiqq.realtestutil.domain.usecase

import com.github.syafiqq.realtestutil.domain.contract.repository.LocalStorageRepositoryInterface
import javax.inject.Inject

interface ClearLocalDataUseCase {
    suspend fun execute()
}

class ClearLocalDataUseCaseImpl @Inject constructor(
    private var localStorageRepositoryInterface: LocalStorageRepositoryInterface,
) : ClearLocalDataUseCase {
    override suspend fun execute() {
        localStorageRepositoryInterface.removeAll()
    }
}
