package org.ray.core.data

import kotlinx.coroutines.flow.Flow
import org.ray.core.data.remote.RemoteDataSource
import org.ray.core.domain.domainModel.Account
import org.ray.core.domain.domainModel.Report
import org.ray.core.domain.repo.IDomainRepository
import org.ray.core.utils.CoreExecutor

class MealRepository(
    private val remote: RemoteDataSource,
    private val coreExecutor: CoreExecutor
) : IDomainRepository {
    override fun getAllAccount(): Flow<Resource<List<Account>>> {
        TODO("Not yet implemented")
    }

    override fun getAllReport(): Flow<Resource<List<Report>>> {
        TODO("Not yet implemented")
    }

}