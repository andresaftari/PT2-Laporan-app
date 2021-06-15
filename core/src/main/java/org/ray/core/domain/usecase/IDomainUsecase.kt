package org.ray.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import org.ray.core.data.Resource
import org.ray.core.data.remote.api.response.ResponseHistory
import org.ray.core.data.remote.api.response.ResponseLogin
import org.ray.core.data.remote.api.response.ResponseRegister
import org.ray.core.data.remote.api.response.ResponseReport

interface IDomainUsecase {
    // Login
    fun postLoginData(
        username: MultipartBody.Part,
        password: MultipartBody.Part
    ): Flow<Resource<ResponseLogin>>

    // Register
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

    // History
    fun getHistoryData(): Flow<Resource<ArrayList<ResponseHistory>>>
}