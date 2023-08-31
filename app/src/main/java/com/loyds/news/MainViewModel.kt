package com.loyds.news

import androidx.lifecycle.viewModelScope
import com.loyds.common.state.BaseUiState
import com.loyds.common.state.BaseViewModel
import com.loyds.common.state.asResult
import com.loyds.common.utils.mutableStateIn
import com.loyds.data.model.Theme
import com.loyds.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: UserPreferencesRepository
) : BaseViewModel<AppUiState>() {

    init {
        _state = repository.userPreferencesFlow.asResult { AppUiState(it.theme, it.dynamicColor) }.mutableStateIn(viewModelScope, BaseUiState.Loading)
    }

    override fun onPageRefresh() {}
}

data class AppUiState(val theme: Theme, val dynamic: Boolean)
