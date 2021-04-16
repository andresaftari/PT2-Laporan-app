package org.ray.core.data.remote

import org.ray.core.data.remote.api.endpoint.ApiService

class RemoteDataSource(private val apiService: ApiService) {
    companion object {
        const val TAG = "RemoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService) = instance ?: synchronized(this) {
            instance ?: RemoteDataSource(apiService)
        }
    }
}