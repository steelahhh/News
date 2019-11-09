@file:Suppress("IncorrectScope")

package dev.steelahhh.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.steelahhh.news.core.Either
import dev.steelahhh.news.core.Failure
import dev.steelahhh.news.domain.Article
import dev.steelahhh.news.domain.NewsRepository
import dev.steelahhh.news.features.ArticlesListState
import dev.steelahhh.news.features.ArticlesViewModel
import dev.steelahhh.news.features.toPreviewUi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ArticlesViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: ArticlesViewModel

    private val repository: NewsRepository = mockk()

    @Before
    fun setup() {
        coEvery { repository.clear() }.coAnswers { }
        vm = ArticlesViewModel(repository)
    }

    @Test
    fun `items are stored`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val mappedArticles = articles.map { it.toPreviewUi() }
        coEvery {
            repository.getNews(
                any(),
                any(),
                any()
            )
        }.returns(Either.Right(articles))
        vm.load()

        vm.news.observeOnce { state ->
            assert(!state.isLoading)
            assert(!state.isError)
            assert(!state.isLoadingMore)
            assert(state.articles == mappedArticles)
        }
    }

    @Test
    fun `error is properly handled`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery {
            repository.getNews(
                any(),
                any(),
                any()
            )
        }.returns(Either.Left(Failure.UnknownError))
        vm.load()

        vm.news.observeOnce { state ->
            assert(
                state == ArticlesListState(
                    isLoading = false,
                    isError = true,
                    isLoadingMore = false,
                    articles = emptyList()
                )
            )
        }
    }

    @Test
    fun `load more works`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery {
            repository.getNews(
                any(),
                any(),
                any()
            )
        }.returns(Either.Right(articles))

        vm.load()
        // check that current page is 1
        val previousPage = vm.currentPage
        assert(previousPage == 1)
        // check that articles size in state matches the static articles size
        vm.news.observeOnce { state -> assert(state.articles.size == articles.size) }
        vm.loadMore()
        // check that after loading next page, size is 2x the static
        vm.news.observeOnce { state -> assert(state.articles.size == articles.size * 2) }
    }

    @Test
    fun `refresh resets the current page`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery {
            repository.getNews(
                any(),
                any(),
                any()
            )
        }.returns(Either.Right(articles))

        // load the initial page
        vm.load()
        // load the next page
        vm.loadMore()
        // refresh the list
        vm.refresh()
        // check that current page is reset
        assert(vm.currentPage == 1)
    }

    @Test
    fun `query change loads the new list`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery {
            repository.getNews(
                any(),
                any(),
                any()
            )
        }.returns(Either.Right(articles))
        // load the initial page
        vm.load()
        // load the next page
        vm.loadMore()
        // check that there are two pages of items
        vm.news.observeOnce { state -> assert(state.articles.size == articles.size * 2) }
        vm.onNewQuery("new query")
        // verify that articles list is reset
        assert(vm.currentPage == 1)
        vm.news.observeOnce { state -> assert(state.articles.size == articles.size) }
    }

    companion object {
        private val articles = (0..10).map { number ->
            Article(
                source = "Test",
                title = "This is a test Article #$number",
                author = "AndroidX",
                image = "",
                description = "Testing ViewModel and repository",
                date = null,
                content = "",
                url = ""
            )
        }
    }
}
