package com.loyds.articles.ui.articles
                 
import com.loyds.common.state.BaseUiState
import com.loyds.data.repository.ArticleRepository
import com.loyds.common.state.BaseViewModel
import com.loyds.articles.models.ArticlesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(

    private val articleRepository: ArticleRepository,
) : BaseViewModel<ArticlesUiState>() {


    init {
        onPageRefresh()
    }

    override fun onPageRefresh() {
        viewModelScope.launch {
            articleRepository.getArticleList().onResult { ArticlesUiState(it) }
        }
    }


}