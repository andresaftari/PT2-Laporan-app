package org.ray.core.data.remote

import android.annotation.SuppressLint
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import org.ray.core.data.remote.api.endpoint.ApiService
import org.ray.core.data.remote.api.response.ResponseStatus
import org.ray.core.utils.REMOTE_DATA_CHECK

@SuppressLint("LogNotTimber")
class RemoteDataSource(private val apiService: ApiService) {
    fun postLoginData(username: MultipartBody.Part, password: MultipartBody.Part) = flow {
        try {
            val response = apiService.postLogin(username, password)

            response.let {
                when (response.status) {
                    "success" -> emit(ResponseStatus.Success(it))
                    else -> emit(ResponseStatus.Error(response.msg))
                }
            }
        } catch (ex: RuntimeException) {
            Log.d(
                "$REMOTE_DATA_CHECK.postLogin",
                "${ex.message} - ${ex.printStackTrace()}"
            )
            emit(ResponseStatus.Error(ex.message.toString()))
        } catch (ex: Exception) {
            Log.d(
                "$REMOTE_DATA_CHECK.postLogin",
                "${ex.message} - ${ex.printStackTrace()}"
            )
            emit(ResponseStatus.Error(ex.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun postRegisterData(
        username: MultipartBody.Part,
        password: MultipartBody.Part,
        email: MultipartBody.Part,
        fullname: MultipartBody.Part
    ) = flow {
        try {
            val response = apiService.postRegister(username, password, fullname, email)

            response.let {
                when (response.status) {
                    "success" -> emit(ResponseStatus.Success(it))
                    else -> emit(ResponseStatus.Error(response.msg))
                }
            }
        } catch (ex: RuntimeException) {
            Log.d(
                "$REMOTE_DATA_CHECK.postRegister",
                "${ex.message} - ${ex.printStackTrace()}"
            )
            emit(ResponseStatus.Error(ex.message.toString()))
        } catch (ex: Exception) {
            Log.d(
                "$REMOTE_DATA_CHECK.postRegister",
                "${ex.message} - ${ex.printStackTrace()}"
            )
            emit(ResponseStatus.Error(ex.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun postReportData(
        username: MultipartBody.Part,
        damage: MultipartBody.Part,
        location: MultipartBody.Part,
        description: MultipartBody.Part,
        photo: MultipartBody.Part
    ) = flow {
        try {
            val response = apiService.postReport(username, damage, location, description, photo)

            response.let {
                when (response.status) {
                    "success" -> emit(ResponseStatus.Success(it))
                    else -> emit(ResponseStatus.Error(response.msg))
                }
            }
        } catch (ex: RuntimeException) {
            Log.d(
                "$REMOTE_DATA_CHECK.postReport",
                "${ex.message} - ${ex.printStackTrace()}"
            )
            emit(ResponseStatus.Error(ex.message.toString()))
        } catch (ex: Exception) {
            Log.d(
                "$REMOTE_DATA_CHECK.postReport",
                "${ex.message} - ${ex.printStackTrace()}"
            )
            emit(ResponseStatus.Error(ex.message.toString()))
        }
    }
}