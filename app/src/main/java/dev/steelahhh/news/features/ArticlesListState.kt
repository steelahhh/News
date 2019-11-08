package dev.steelahhh.news.features

data class ArticlesListState(
    val query: String = "",
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val isError: Boolean = false,
    val articles: List<ArticlePreviewUi> = emptyList()
)
