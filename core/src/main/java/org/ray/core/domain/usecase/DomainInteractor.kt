package org.ray.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import org.ray.core.data.remote.api.response.ResponseLogin
import org.ray.core.domain.repo.IDomainRepository

class DomainInteractor(private val iDomainRepository: IDomainRepository) : IDomainUsecase {
    override suspend fun postLoginData(
        username: MultipartBody.Part,
        password: MultipartBody.Part
    ): Flow<ResponseLogin> = iDomainRepository.postLoginData(username, password)
}