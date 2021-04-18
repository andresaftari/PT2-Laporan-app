package org.ray.core.data.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import org.ray.core.data.remote.api.endpoint.ApiService
import org.ray.core.utils.REMOTE_DATA_CHECK

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun postLoginData(username: MultipartBody.Part, password: MultipartBody.Part) = flow {
        try {
            val response = apiService.postLogin(username, password)

            if (!response.status.equals("fail", true)) emit(response)
            else emit(response)
        } catch (ex: RuntimeException) {
            Log.d(
                "$REMOTE_DATA_CHECK.postLogin",
                "${ex.message} - ${ex.printStackTrace()}"
            )
        } catch (ex: Exception) {
            Log.d(
                "$REMOTE_DATA_CHECK.postLogin",
                "${ex.message} - ${ex.printStackTrace()}"
            )
        }
    }.flowOn(Dispatchers.IO)
}