package com.loyds.news

import com.loyds.articles.models.ArticlesUiState
import com.loyds.articles.ui.articles.ArticlesViewModel
import com.loyds.common.state.BaseUiState
import com.loyds.data.repository.ArticleRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArticlesViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @RelaxedMockK
    lateinit var mockArticleRepository: ArticleRepository

    private lateinit var viewModel: ArticlesViewModel


    @Before
    fun setup() {
        MockKAnnotations.init(this)


    }

    @Test
    fun whenOnViewModelInit_GivenGetArticleFails_ThenStateError() = runTest {

        coEvery { mockArticleRepository.getArticleList() } returns Result.failure(Exception())

        viewModel = ArticlesViewModel(articleRepository = mockArticleRepository)

        coVerify(exactly = 1) { mockArticleRepository.getArticleList() }

        assert(viewModel.state.value is BaseUiState.Error)
    }

    @Test
    fun whenOnViewModelInit_GivenGetArticleFails_ThenStateLoaded() = runTest {

        coEvery { mockArticleRepository.getArticleList() } returns Result.success(emptyList())

        viewModel = ArticlesViewModel(articleRepository = mockArticleRepository)

        coVerify(exactly = 1) { mockArticleRepository.getArticleList() }

        assertEquals(viewModel.state.value, BaseUiState.Loaded(ArticlesUiState()))
    }
}
