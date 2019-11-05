package dev.steelahhh.news.data.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dev.steelahhh.news.data.db.NewsDao
import dev.steelahhh.news.data.db.NewsDatabase
import dev.steelahhh.news.data.network.ApiKeyInterceptor
import dev.steelahhh.news.data.network.NewsService
import dev.steelahhh.news.data.network.models.NewsResponseFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object DataModule {

    private const val TIME_OUT = 3000L
    private const val BASE_URL = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideChucker(context: Context) = ChuckerInterceptor(context)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(NewsResponseFactory())
        .build()

    @Provides
    @Reusable
    fun provideOkHttpClient(chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor)
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    @Provides
    @Singleton
    fun provideRoom(context: Context): NewsDatabase = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        "news"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.newsDao
}
