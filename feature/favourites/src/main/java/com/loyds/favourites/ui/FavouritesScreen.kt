package com.loyds.favourites.ui.favourites

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.loyds.favourites.models.FavouritesUiState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.font.FontLoadingStrategy.Companion.Async
import com.loyds.common.state.EmptyState
import com.loyds.designsystem.component.common.Article
import com.loyds.designsystem.theme.NewsTheme

@Composable
fun FavouritesRoute(
    navigateToDetails: (String) -> Unit,
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    viewModel.HandleUiState() { modifier, state ->
        FavouritesScreen(modifier = modifier, state = state, navigateToDetails = navigateToDetails, )
    }
}

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    state: FavouritesUiState,
    navigateToDetails: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(NewsTheme.dimens.medium),
        verticalArrangement = Arrangement.spacedBy(NewsTheme.dimens.medium)
    ) {
    
        
        items(state.content) {
            Article(it, navigateToDetails)

            Async
        }
    }
    
    if(state.content.isEmpty()) Box(modifier = modifier.fillMaxSize()) {
        EmptyState("Favourites")
    }
}