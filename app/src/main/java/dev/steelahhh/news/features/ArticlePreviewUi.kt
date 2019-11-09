package dev.steelahhh.news.features

import android.text.format.DateUtils
import dev.steelahhh.news.domain.Article

data class ArticlePreviewUi(
    val title: String,
    val image: String,
    val date: String,
    val description: String
)

fun Article.toPreviewUi() = ArticlePreviewUi(
    title = title,
    image = image,
    date = date?.let {
        DateUtils.getRelativeTimeSpanString(
            date.toEpochMilli(),
            System.currentTimeMillis(),
            0L
        ).toString()
    }.orEmpty(),
    description = description
)
