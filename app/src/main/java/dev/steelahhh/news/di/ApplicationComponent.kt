package dev.steelahhh.news.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.steelahhh.news.features.NewsModule
import dev.steelahhh.news.features.NewsViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    val newsListViewModel: NewsViewModel
}
