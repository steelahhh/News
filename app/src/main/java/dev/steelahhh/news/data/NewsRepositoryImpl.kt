package dev.steelahhh.news.data

import dev.steelahhh.news.core.Either
import dev.steelahhh.news.core.Failure
import dev.steelahhh.news.data.db.NewsDao
import dev.steelahhh.news.data.network.NewsService
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse
import dev.steelahhh.news.data.network.models.ApiResponse.SuccessResponse
import dev.steelahhh.news.domain.Article
import dev.steelahhh.news.domain.NewsRepository
import dev.steelahhh.news.domain.toDomain
import java.net.UnknownHostException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val dao: NewsDao
) : NewsRepository {
    override suspend fun getNews(
        query: String,
        page: Int,
        pageSize: Int
    ): Either<Failure, List<Article>> {
        return try {
            val response = service.getEverything(
                page = page,
                query = query,
                pageSize = pageSize
            )
            when (response) {
                is SuccessResponse -> Either.Right(response.articles.map { it.toDomain() })
                is ErrorResponse -> Either.Left(Failure.FeatureFailure(response.code))
            }
        } catch (exception: Exception) {
            when (exception) {
                is UnknownHostException -> Either.Left(Failure.NetworkConnection)
                else -> Either.Left(Failure.UnknownError)
            }
        }
    }
}
