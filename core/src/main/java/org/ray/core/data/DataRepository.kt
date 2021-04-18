package org.ray.core.data

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import org.ray.core.data.remote.RemoteDataSource
import org.ray.core.data.remote.api.response.ResponseLogin
import org.ray.core.domain.repo.IDomainRepository
import org.ray.core.utils.CoreExecutor

class DataRepository(
    private val remote: RemoteDataSource,
    private val coreExecutor: CoreExecutor
) : IDomainRepository {
    override suspend fun postLoginData(
        username: MultipartBody.Part,
        password: MultipartBody.Part
    ): Flow<ResponseLogin> = remote.postLoginData(username, password)
}