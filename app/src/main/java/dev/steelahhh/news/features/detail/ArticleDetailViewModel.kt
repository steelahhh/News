package dev.steelahhh.news.features.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.steelahhh.news.domain.NewsRepository
import javax.inject.Inject
import kotlinx.coroutines.launch

class ArticleDetailViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    val article get() = _article
    private val _article = MutableLiveData<ArticleUi>()

    fun getByTitle(title: String) {
        viewModelScope.launch {
            _article.value = repository.getNewsByTitle(title).toUi()
        }
    }
}
