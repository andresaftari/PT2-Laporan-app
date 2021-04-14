package org.ray.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.ray.core.data.Resource
import org.ray.core.domain.domainModel.Account
import org.ray.core.domain.domainModel.Report

interface IDomainUsecase {
    // Account
    fun getAllAccount(): Flow<Resource<List<Account>>>

    // Report
    fun getAllReport(): Flow<Resource<List<Report>>>
}