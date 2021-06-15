package org.ray.nyarioskeun.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import org.ray.core.data.Resource
import org.ray.core.data.remote.api.response.ResponseHistory
import org.ray.core.domain.usecase.IDomainUsecase

class HistoryViewModel(private val domainUsecase: IDomainUsecase) : ViewModel() {
    fun getHistory(): LiveData<Resource<ArrayList<ResponseHistory>>> =
        domainUsecase.getHistoryData().asLiveData()
}