package org.ray.core.injections

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.ray.core.data.DataRepository
import org.ray.core.data.remote.RemoteDataSource
import org.ray.core.data.remote.api.endpoint.ApiService
import org.ray.core.domain.repo.IDomainRepository
import org.ray.core.utils.API_BASE_URL
import org.ray.core.utils.CoreExecutor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Setup API remote dependency injection
val remoteModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

// Setup data source dependency injection
val sourceModule = module {
    single { RemoteDataSource(get()) }
}

// Setup data repository dependency injection
val repoModule = module {
    factory { CoreExecutor() }
    single<IDomainRepository> { DataRepository(get()) }
}