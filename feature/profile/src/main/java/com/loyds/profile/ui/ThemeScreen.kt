package com.loyds.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loyds.designsystem.theme.NewsTheme
import com.loyds.data.model.Theme
import com.loyds.profile.R
import com.loyds.profile.component.SelectButton

@Composable
fun ThemeRoute(
    viewModel: ThemeViewModel = hiltViewModel(),
) {
    viewModel.HandleUiState { modifier, state ->
        ThemeScreen(
            modifier = modifier,
            state = state,
            updateTheme = viewModel::updateTheme,
            updateColors = viewModel::updateColors
        )
    }
}

@Composable
fun ThemeScreen(
    modifier: Modifier = Modifier,
    state: ThemeUiState,
    updateTheme: (Theme) -> Unit,
    updateColors: (Boolean) -> Unit,
) {
    Column(
        modifier.padding(NewsTheme.dimens.medium),
        verticalArrangement = Arrangement.spacedBy(
            NewsTheme.dimens.medium
        )
    ) {

        Card {
            LazyColumn {
                itemsIndexed(Theme.values()) { index, item ->

                    SelectButton(item.toTitle(), state.mode == item) { updateTheme(item) }

                    if (index < Theme.values().lastIndex) Box(
                        modifier = modifier
                            .height(0.5.dp)
                            .background(color = MaterialTheme.colorScheme.outlineVariant)
                            .fillMaxWidth()
                            .padding(
                                horizontal = NewsTheme.dimens.medium
                            )
                    )
                }
            }
        }

        Card {
            SelectButton(text = stringResource(id = R.string.dynamic_color), state.dynamic) {
                updateColors(!state.dynamic)
            }
        }
    }
}
