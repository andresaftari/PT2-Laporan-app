package org.ray.nyarioskeun

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.ray.core.injections.remoteModule
import org.ray.core.injections.repoModule
import org.ray.core.injections.sourceModule
import org.ray.nyarioskeun.utils.useCaseModule
import org.ray.nyarioskeun.utils.viewModel
import timber.log.Timber

// Start Koin dependency injection
class MApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MApplication)
            modules(
                listOf(
                    remoteModule,
                    sourceModule,
                    useCaseModule,
                    viewModel,
                    repoModule
                )
            )
        }

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}