package dev.steelahhh.news.data.network.models

import com.squareup.moshi.JsonClass
import org.threeten.bp.Instant

@JsonClass(generateAdapter = true)
data class ArticleResponse(
    val source: SourceResponse,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: Instant,
    val content: String?
) {

    @JsonClass(generateAdapter = true)
    data class SourceResponse(
        val id: String?,
        val name: String
    )
}
