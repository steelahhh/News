package dev.steelahhh.news.data.network

import dev.steelahhh.news.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val newHttpUrl = original.url().newBuilder()
            .addQueryParameter("apiKey", BuildConfig.API_KEY)
            .build()

        val newRequest = original.newBuilder().url(newHttpUrl).build()

        return chain.proceed(newRequest)
    }
}
