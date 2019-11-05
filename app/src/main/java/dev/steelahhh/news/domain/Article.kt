package dev.steelahhh.news.domain

import dev.steelahhh.news.data.network.models.ArticleResponse

data class Article(
    val title: String,
    val description: String,
    val image: String,
    val url: String,
    val content: String
)

fun ArticleResponse.toDomain() = Article(
    title = title,
    description = description.orEmpty(),
    image = urlToImage.orEmpty(),
    content = content.orEmpty(),
    url = url
)
