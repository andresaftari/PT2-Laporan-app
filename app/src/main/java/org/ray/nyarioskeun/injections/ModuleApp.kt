package org.ray.nyarioskeun.utils

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.ray.core.domain.usecase.DomainInteractor
import org.ray.core.domain.usecase.IDomainUsecase
import org.ray.nyarioskeun.ui.auth.AuthViewModel
import org.ray.nyarioskeun.ui.report.ReportViewModel

// Setup data usecase dependency injection
val useCaseModule = module {
    factory<IDomainUsecase> { DomainInteractor(get()) }
}

// Setup viewmodel dependency injection
val viewModel = module {
    viewModel { AuthViewModel(get()) }
    viewModel { ReportViewModel(get()) }
}