package org.ray.core.domain.repo

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import org.ray.core.data.remote.api.response.ResponseLogin

interface IDomainRepository {
    // Account
    suspend fun postLoginData(
        username: MultipartBody.Part,
        password: MultipartBody.Part
    ): Flow<ResponseLogin>
}