package dev.steelahhh.news.domain

import dev.steelahhh.news.core.Either
import dev.steelahhh.news.core.Failure

interface NewsRepository {
    suspend fun getNews(query: String, page: Int, pageSize: Int): Either<Failure, List<Article>>
    suspend fun getNewsById(id: Int): Article
    suspend fun getNewsByTitle(title: String): Article
    suspend fun clear()
}
