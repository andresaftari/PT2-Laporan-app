package org.ray.nyarioskeun.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import okhttp3.MultipartBody
import org.ray.core.data.Resource
import org.ray.core.data.remote.api.response.ResponseReport
import org.ray.core.domain.usecase.IDomainUsecase

class ReportViewModel(private val domainUsecase: IDomainUsecase) : ViewModel() {
    fun setReport(
        username: MultipartBody.Part,
        kerusakan: MultipartBody.Part,
        lokasi: MultipartBody.Part,
        deskripsi: MultipartBody.Part,
        photo: MultipartBody.Part
    ): LiveData<Resource<ResponseReport>> =
        domainUsecase.postReportData(username, kerusakan, lokasi, deskripsi, photo).asLiveData()
}