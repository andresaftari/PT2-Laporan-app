package org.ray.nyarioskeun.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import okhttp3.MultipartBody
import org.ray.core.data.Resource
import org.ray.core.data.remote.api.response.ResponseLogin
import org.ray.core.domain.usecase.IDomainUsecase

class AuthViewModel(
    private val domainUsecase: IDomainUsecase
) : ViewModel() {
    fun setLogin(
        username: MultipartBody.Part,
        password: MultipartBody.Part
    ): LiveData<Resource<ResponseLogin>> =
        domainUsecase.postLoginData(username, password).asLiveData()
}