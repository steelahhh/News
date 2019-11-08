package dev.steelahhh.news.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dev.steelahhh.news.R
import dev.steelahhh.news.core.loaderItem
import dev.steelahhh.news.di.injector
import dev.steelahhh.news.di.viewModel
import dev.steelahhh.news.features.ArticlesListFragmentDirections.Companion.openArticleDetail
import dev.steelahhh.news.features.ArticlesViewModel.Companion.PAGE_SIZE
import kotlin.math.abs
import kotlinx.android.synthetic.main.fragment_article_list.*

class ArticlesListFragment : Fragment() {
    private val vm by viewModel { injector.newsListViewModel }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_article_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm.news.observe(viewLifecycleOwner, Observer { state ->
            when {
                state.isLoading -> renderLoading()
                state.articles.isNotEmpty() -> renderContent(state)
                state.isError -> renderError(state)
            }
        })
    }

    private fun renderError(state: ArticlesListState) {
        // TODO handle error when loading more
        if (state.articles.isEmpty()) {
            errorView.isVisible = true
            progressBar.isGone = true
            recycler.isGone = true
            errorView.onRetryClick = vm::refresh
        }
    }

    private fun renderLoading() {
        errorView.isGone = true
        progressBar.isVisible = true
    }

    private fun renderContent(state: ArticlesListState) {
        errorView.isGone = true
        progressBar.isGone = true
        recycler.isVisible = true
        recycler.withModels {
            state.articles.forEach { article ->
                articleItem {
                    id(article.title + article.description)
                    article(article)
                    listener {
                        val action = openArticleDetail(it.title)
                        findNavController().navigate(action)
                    }
                    onBind { _, _, position ->
                        if (position == abs(state.articles.size - PAGE_SIZE / 2)) {
                            vm.loadMore()
                        }
                    }
                }
            }
            if (state.isLoadingMore) {
                loaderItem {
                    id("Loader${state.articles.size}")
                }
            }
        }
    }
}
