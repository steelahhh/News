package dev.steelahhh.news.domain

import dev.steelahhh.news.core.Either
import dev.steelahhh.news.core.Failure

interface NewsRepository {
    /**
     * Load the news given parameters,
     *  when we succeed, return [Either.Right],
     *  otherwise return [Either.Left]
     */
    suspend fun getNews(query: String, page: Int, pageSize: Int): Either<Failure, List<Article>>

    /**
     * Find the article by [title] since there are no IDs in news api response
     */
    suspend fun getNewsByTitle(title: String): Article

    /**
     * Clear all the articles
     */
    suspend fun clear()
}
