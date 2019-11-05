package dev.steelahhh.news.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.steelahhh.news.data.di.DataModule
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}
