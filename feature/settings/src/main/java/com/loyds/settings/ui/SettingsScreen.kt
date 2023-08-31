package com.loyds.settings.ui.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.loyds.settings.models.SettingsUiState


@Composable
fun SettingsRoute(

    viewModel: SettingsViewModel = hiltViewModel()
) {
    viewModel.HandleUiState() { modifier, state ->
        SettingsScreen(modifier = modifier, state = state, )
    }
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    state: SettingsUiState,
    
) {

}