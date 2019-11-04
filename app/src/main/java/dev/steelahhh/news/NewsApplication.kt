package dev.steelahhh.news

import android.app.Application
import dev.steelahhh.news.di.ApplicationComponent
import dev.steelahhh.news.di.DaggerApplicationComponent
import dev.steelahhh.news.di.InjectorProvider
import timber.log.Timber

@Suppress("unused")
class NewsApplication : Application(), InjectorProvider {

    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}
