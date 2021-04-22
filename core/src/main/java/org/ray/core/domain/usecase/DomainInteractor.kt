package org.ray.core.domain.usecase

import okhttp3.MultipartBody
import org.ray.core.domain.repo.IDomainRepository

class DomainInteractor(private val iDomainRepository: IDomainRepository) : IDomainUsecase {
    override fun postLoginData(
        username: MultipartBody.Part,
        password: MultipartBody.Part
    ) = iDomainRepository.postLoginData(username, password)

    override fun postRegisterData(
        username: MultipartBody.Part,
        password: MultipartBody.Part,
        fullname: MultipartBody.Part,
        email: MultipartBody.Part
    ) = iDomainRepository.postRegisterData(username, password, fullname, email)

    override fun postReportData(
        username: MultipartBody.Part,
        damage: MultipartBody.Part,
        location: MultipartBody.Part,
        description: MultipartBody.Part,
        photo: MultipartBody.Part
    ) = iDomainRepository.postReportData(username, damage, location, description, photo)
}