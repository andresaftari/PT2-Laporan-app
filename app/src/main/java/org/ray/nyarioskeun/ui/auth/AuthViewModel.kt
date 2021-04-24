package org.ray.nyarioskeun.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import okhttp3.MultipartBody
import org.ray.core.data.Resource
import org.ray.core.data.remote.api.response.ResponseLogin
import org.ray.core.data.remote.api.response.ResponseRegister
import org.ray.core.domain.usecase.IDomainUsecase

class AuthViewModel(private val domainUsecase: IDomainUsecase) : ViewModel() {
    fun setLogin(
        username: MultipartBody.Part,
        password: MultipartBody.Part
    ): LiveData<Resource<ResponseLogin>> =
        domainUsecase.postLoginData(username, password).asLiveData()

    fun setRegister(
        fullname: MultipartBody.Part,
        username: MultipartBody.Part,
        password: MultipartBody.Part,
        email: MultipartBody.Part
    ): LiveData<Resource<ResponseRegister>> =
        domainUsecase.postRegisterData(username, password, fullname, email).asLiveData()
}