package com.loyds.details.ui

import com.loyds.common.state.BaseUiState
import com.loyds.data.repository.ArticleRepository

import com.loyds.common.utils.INVALID_ID
import androidx.lifecycle.SavedStateHandle
import com.loyds.details.navigation.DetailsArgs
import java.security.InvalidParameterException
import com.loyds.common.state.BaseViewModel
import com.loyds.details.models.DetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.loyds.model.data.Article
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(

    savedStateHandle: SavedStateHandle,
    private val articleRepository: ArticleRepository,
) : BaseViewModel<DetailsUiState>() {

    private val detailsArgs: DetailsArgs = DetailsArgs(savedStateHandle)

    init {
        onPageRefresh()
    }

    override fun onPageRefresh() {
        if (detailsArgs.id == INVALID_ID) {
            _state.update { BaseUiState.Error(InvalidParameterException()) }
        } else {
            viewModelScope.launch {
                articleRepository.getArticleById(detailsArgs.id).onResult { DetailsUiState(it) }
            }
        }
    }

    fun updateFavourite(article: Article) {
        if (article.favourite) {
            removeFromFavourites()
        } else {
            saveToFavourites(article)
        }
    }

    private fun saveToFavourites(article: Article) {
        viewModelScope.launch {
            val result = articleRepository.addFavourite(article)

            onLoaded {
                DetailsUiState(article.copy(favourite = true))
            }
        }
    }

    private fun removeFromFavourites() {
        viewModelScope.launch {
            articleRepository.removeFavourite(detailsArgs.id)

            onLoaded {
                DetailsUiState(it.content.copy(favourite = false))
            }
        }
    }

}