package org.ray.nyarioskeun.utils

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.ray.core.domain.usecase.DomainInteractor
import org.ray.core.domain.usecase.IDomainUsecase
import org.ray.nyarioskeun.ui.auth.AuthViewModel

val useCaseModule = module {
    factory<IDomainUsecase> { DomainInteractor(get()) }
}

val viewModel = module {
    viewModel { AuthViewModel(get()) }
}