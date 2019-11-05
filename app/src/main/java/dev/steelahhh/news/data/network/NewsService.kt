package dev.steelahhh.news.data.network

import dev.steelahhh.news.data.network.models.ApiResponse
import dev.steelahhh.news.data.network.models.Language
import dev.steelahhh.news.data.network.models.SortBy
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    suspend fun getEverything(
        @Query("page") page: Int,
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int,
        @Query("language") language: Language = Language.EN,
        @Query("sortBy") sortBy: SortBy = SortBy.PUBLISH_DATE
    ): ApiResponse
}
