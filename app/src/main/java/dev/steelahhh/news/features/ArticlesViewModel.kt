package dev.steelahhh.news.features

import androidx.lifecycle.ViewModel
import dev.steelahhh.news.domain.NewsRepository
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

}
