package org.ray.nyarioskeun.data.remote.api.apiService

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.ray.nyarioskeun.data.remote.api.endpoint.AuthService
import org.ray.nyarioskeun.data.remote.api.endpoint.ReportService
import org.ray.nyarioskeun.utils.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {
    private var retrofit: Retrofit? = null

    private val okHttpClientBuilder = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .build()

    fun <S> createService(serviceClass: Class<S>?): S {
        if (retrofit == null) retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_BASE_URL)
                .client(okHttpClientBuilder)
                .build()

        return retrofit!!.create(serviceClass!!)
    }

    val reportService: ReportService by lazy { createService(ReportService::class.java) }
    val authService: AuthService by lazy { createService(AuthService::class.java) }
}