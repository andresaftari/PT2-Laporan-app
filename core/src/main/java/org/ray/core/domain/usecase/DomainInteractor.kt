package org.ray.core.domain.usecase

import org.ray.core.domain.repo.IDomainRepository

class DomainInteractor(private val iDomainRepository: IDomainRepository) : IDomainUsecase {
    override fun getAllAccount() = iDomainRepository.getAllAccount()
    override fun getAllReport() = iDomainRepository.getAllReport()
}