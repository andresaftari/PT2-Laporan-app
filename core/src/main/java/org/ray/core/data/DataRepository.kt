package org.ray.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import org.ray.core.data.remote.RemoteDataSource
import org.ray.core.data.remote.api.response.ResponseLogin
import org.ray.core.data.remote.api.response.ResponseRegister
import org.ray.core.data.remote.api.response.ResponseReport
import org.ray.core.data.remote.api.response.ResponseStatus
import org.ray.core.domain.repo.IDomainRepository

class DataRepository(
    private val remote: RemoteDataSource,
) : IDomainRepository {
    override fun postLoginData(
        username: MultipartBody.Part,
        password: MultipartBody.Part
    ): Flow<Resource<ResponseLogin>> = flow {
        emit(Resource.loading())
        val response = remote.postLoginData(username, password)

        when (val apiResponse = response.first()) {
            is ResponseStatus.Success -> emit(Resource.success(apiResponse.data))
            is ResponseStatus.Empty -> emit(Resource.success(ResponseLogin()))
            is ResponseStatus.Error -> emit(Resource.error<ResponseLogin>(apiResponse.errorMessage))
        }
    }

    override fun postRegisterData(
        username: MultipartBody.Part,
        password: MultipartBody.Part,
        fullname: MultipartBody.Part,
        email: MultipartBody.Part
    ): Flow<Resource<ResponseRegister>> = flow {
        emit(Resource.loading())
        val response = remote.postRegisterData(username, password, email, fullname)

        when (val apiResponse = response.first()) {
            is ResponseStatus.Success -> emit(Resource.success(apiResponse.data))
            is ResponseStatus.Empty -> emit(Resource.success(ResponseRegister()))
            is ResponseStatus.Error -> emit(Resource.error<ResponseRegister>(apiResponse.errorMessage))
        }
    }

    override fun postReportData(
        username: MultipartBody.Part,
        damage: MultipartBody.Part,
        location: MultipartBody.Part,
        description: MultipartBody.Part,
        photo: MultipartBody.Part
    ): Flow<Resource<ResponseReport>> = flow {
        emit(Resource.loading())
        val response = remote.postReportData(username, damage, location, description, photo)

        when (val apiResponse = response.first()) {
            is ResponseStatus.Success -> emit(Resource.success(apiResponse.data))
            is ResponseStatus.Empty -> emit(Resource.success(ResponseReport()))
            is ResponseStatus.Error -> emit(Resource.error<ResponseReport>(apiResponse.errorMessage))
        }
    }
}