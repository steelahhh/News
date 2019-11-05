package dev.steelahhh.news.features

import dagger.Binds
import dagger.Module
import dev.steelahhh.news.data.NewsRepositoryImpl
import dev.steelahhh.news.data.di.DataModule
import dev.steelahhh.news.domain.NewsRepository

@Module(includes = [DataModule::class])
abstract class NewsModule {

    @Binds
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}
