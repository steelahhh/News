package dev.steelahhh.news.features

/**
 * An object that represents the state of articles list screen.
 *
 * @param isLoading - true when first loading or refreshing the list, or changing the query
 * @param isLoadingMore - mark as true when we reach the fetch new page area
 * @param isError - simple param to indicate error,
 *  but it could be expanded into an enum representing different error types
 * @param articles - a list of articles to be displayed on the screen
 */
data class ArticlesListState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val isError: Boolean = false,
    val articles: List<ArticlePreviewUi> = emptyList()
)
