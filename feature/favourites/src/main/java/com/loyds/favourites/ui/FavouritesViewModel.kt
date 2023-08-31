package com.loyds.favourites.ui.favourites
                 
import com.loyds.common.state.BaseUiState
import com.loyds.common.state.asResult
import com.loyds.common.utils.mutableStateIn
import kotlinx.coroutines.flow.collectLatest
import com.loyds.data.repository.ArticleRepository
import com.loyds.common.state.BaseViewModel
import com.loyds.favourites.models.FavouritesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(

    private val articleRepository: ArticleRepository,
) : BaseViewModel<FavouritesUiState>() {


    init {
        onPageRefresh()
        
        _state = articleRepository.getFavouriteArticles().asResult { FavouritesUiState(it) }.mutableStateIn(viewModelScope, BaseUiState.Loading)
    }

    override fun onPageRefresh() {

    }


}