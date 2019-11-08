package dev.steelahhh.news.features.detail

import android.text.format.DateUtils
import dev.steelahhh.news.domain.Article

data class ArticleUi(
    val title: String,
    val info: String,
    val date: String,
    val image: String,
    val content: String,
    val url: String
)

fun Article.toUi() = ArticleUi(
    title = title,
    info = listOf(author, source).joinToString(),
    date = DateUtils.getRelativeTimeSpanString(
        date.toEpochMilli(),
        System.currentTimeMillis(),
        0L
    ).toString(),
    url = url,
    content = content,
    image = image
)
