package dev.steelahhh.news.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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

    private val searchView by lazy {
        (toolbar.menu.findItem(R.id.btn_search_view).actionView as SearchView)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_article_list, container, false)

    override fun onResume() {
        super.onResume()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean = false
            override fun onQueryTextSubmit(query: String?): Boolean = true.also {
                query?.run(vm::onNewQuery)
            }
        }) }

    override fun onPause() {
        super.onPause()
        searchView.setOnQueryTextListener(null)
    }

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
        recycler.isGone = true
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
                        findNavController().navigate(openArticleDetail(it.title))
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
