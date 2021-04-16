package org.ray.core.data.remote.api.endpoint

import okhttp3.MultipartBody
import org.ray.core.data.remote.api.response.ResponseLogin
import org.ray.core.utils.LOGIN_ENDPOINT
import org.ray.core.utils.REGISTER_ENDPOINT
import org.ray.core.utils.REPORT_ENDPOINT
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

// API Endpoint untuk user authentication
interface ApiService {
    // REGISTER
    @Multipart
    @POST(REGISTER_ENDPOINT)
    suspend fun postRegister()

    // LOGIN
    @Multipart
    @POST(LOGIN_ENDPOINT)
    suspend fun postLogin(
        @Part username: MultipartBody.Part,
        @Part password: MultipartBody.Part
    ): ResponseLogin

    // REPORT
    @Headers("Content-Type: multipart/form-data")
    @Multipart
    @POST(REPORT_ENDPOINT)
    suspend fun postReport()
}