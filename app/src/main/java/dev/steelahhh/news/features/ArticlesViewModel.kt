package dev.steelahhh.news.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.steelahhh.news.core.Failure
import dev.steelahhh.news.domain.Article
import dev.steelahhh.news.domain.NewsRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticlesViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    val news get() = _news

    private val _news = MutableLiveData<ArticlesListState>()
    private val _query = MutableLiveData<String>("bitcoin")

    private var currentPage = 1

    private val currentQuery get() = _query.value.orEmpty()

    init {
        viewModelScope.launch { repository.clear() }
        news.value = ArticlesListState(isLoading = true)
        loadNews(currentPage, currentQuery)
    }

    fun onNewQuery(newQuery: String) {
        _query.value = newQuery
        refresh()
    }

    fun loadMore() {
        currentPage++
        loadNews(currentPage, currentQuery)
    }

    fun refresh() {
        currentPage = 1
        news.value = ArticlesListState(isLoading = true, articles = emptyList())
        loadNews(currentPage, currentQuery)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun handleFailure(failure: Failure) {
        // TODO handle different types of failure
        news.value = ArticlesListState(isLoading = false, isError = true)
    }

    private fun handleSuccess(list: List<Article>) {
        news.value = ArticlesListState(
            isLoading = false,
            isLoadingMore = false,
            isError = false,
            articles = (news.value?.articles.orEmpty()) + list.map {
                it.toPreviewUi()
            }
        )
    }

    private fun loadNews(
        page: Int,
        query: String
    ) = viewModelScope.launch {
        val result = withContext(Dispatchers.Default) {
            repository.getNews(query = query, page = page, pageSize = PAGE_SIZE)
        }
        result.either(::handleFailure, ::handleSuccess)
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}
