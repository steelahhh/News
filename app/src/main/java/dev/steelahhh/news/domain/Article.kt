package dev.steelahhh.news.domain

import dev.steelahhh.news.data.entities.NewsEntity
import dev.steelahhh.news.data.network.models.ArticleResponse
import org.threeten.bp.Instant

data class Article(
    val source: String,
    val title: String,
    val author: String,
    val description: String,
    val image: String,
    val url: String,
    val date: Instant,
    val content: String
)

fun ArticleResponse.toDomain() = Article(
    title = title,
    author = author.orEmpty(),
    description = description.orEmpty(),
    image = urlToImage.orEmpty(),
    content = content.orEmpty(),
    url = url,
    source = source.name,
    date = publishedAt
)

fun Article.toDb() = NewsEntity(
    source = source,
    author = author,
    title = title,
    description = description,
    url = url,
    imageUrl = image,
    publishDate = date.toString(),
    content = content
)

fun NewsEntity.toDomain() = Article(
    title = title,
    author = author,
    description = description,
    image = imageUrl,
    content = content,
    url = url,
    source = source,
    date = Instant.parse(publishDate)
)
