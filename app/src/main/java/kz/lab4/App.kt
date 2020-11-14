package kz.lab4

import android.app.Application
import android.content.Context
import kz.lab4.presentation.di.localModule
import kz.lab4.presentation.di.repositoryModule
import kz.lab4.presentation.di.useCaseModule
import kz.lab4.presentation.di.viewModelModule
import kz.lab4.utils.BuildType
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var appContext: Context
    }

    private val todoModules = listOf(
        localModule,
        repositoryModule,
        viewModelModule,
        useCaseModule
    )

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        Timber.i("onCreate")
        appContext = applicationContext

        startKoin {
            fileProperties()
            androidLogger()
            androidContext(appContext)
            modules(todoModules)
        }
    }

    private fun setupTimber() {
        if (BuildType.isDebug()) Timber.plant(Timber.DebugTree())
    }
}