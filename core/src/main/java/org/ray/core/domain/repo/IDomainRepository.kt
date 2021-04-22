package org.ray.core.domain.repo

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import org.ray.core.data.Resource
import org.ray.core.data.remote.api.response.ResponseLogin
import org.ray.core.data.remote.api.response.ResponseRegister
import org.ray.core.data.remote.api.response.ResponseReport

interface IDomainRepository {
    // Account
    fun postLoginData(
        username: MultipartBody.Part,
        password: MultipartBody.Part
    ): Flow<Resource<ResponseLogin>>

    fun postRegisterData(
        username: MultipartBody.Part,
        password: MultipartBody.Part,
        fullname: MultipartBody.Part,
        email: MultipartBody.Part
    ): Flow<Resource<ResponseRegister>>

    // Report
    fun postReportData(
        username: MultipartBody.Part,
        damage: MultipartBody.Part,
        location: MultipartBody.Part,
        description: MultipartBody.Part,
        photo: MultipartBody.Part
    ): Flow<Resource<ResponseReport>>
}