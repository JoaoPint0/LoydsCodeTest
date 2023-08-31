package com.loyds.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewModelScope
import com.loyds.common.state.BaseUiState
import com.loyds.common.state.BaseViewModel
import com.loyds.common.state.asResult
import com.loyds.common.utils.mutableStateIn
import com.loyds.data.model.Theme
import com.loyds.data.repository.UserPreferencesRepository
import com.loyds.profile.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ThemeUiState(val mode: Theme, val dynamic: Boolean)

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val repository: UserPreferencesRepository,
) : BaseViewModel<ThemeUiState>() {

    init {
        _state = repository.userPreferencesFlow.asResult { ThemeUiState(it.theme, it.dynamicColor) }
            .mutableStateIn(viewModelScope, BaseUiState.Loading)
    }

    override fun onPageRefresh() {}
    fun updateTheme(theme: Theme) {
        viewModelScope.launch {
            repository.updateTheme(theme)
        }
    }

    fun updateColors(dynamic: Boolean) {
        viewModelScope.launch {
            repository.updateDynamicColors(dynamic)
        }
    }
}

@Composable
fun Theme.toTitle(): String {
    return stringResource(
        id = when (this) {
            Theme.DARK -> R.string.dark_mode
            Theme.LIGHT -> R.string.light_mode
            Theme.SYSTEM -> R.string.system_settings
        }
    )
}
