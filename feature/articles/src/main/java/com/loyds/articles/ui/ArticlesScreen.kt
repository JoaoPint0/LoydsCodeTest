package com.loyds.articles.ui.articles

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.loyds.articles.models.ArticlesUiState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.loyds.common.state.EmptyState
import com.loyds.designsystem.component.common.Article
import com.loyds.designsystem.theme.NewsTheme
import com.loyds.model.data.Article

@Composable
fun ArticlesRoute(
    navigateToDetails: (String) -> Unit,
    viewModel: ArticlesViewModel = hiltViewModel(),
) {
    viewModel.HandleUiState() { modifier, state ->
        ArticlesScreen(modifier = modifier, state = state, navigateToDetails = navigateToDetails)
    }
}

@Composable
fun ArticlesScreen(
    modifier: Modifier = Modifier,
    state: ArticlesUiState,
    navigateToDetails: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(NewsTheme.dimens.medium),
        verticalArrangement = Arrangement.spacedBy(NewsTheme.dimens.medium)
    ) {

        items(state.content) { article ->

            Article(article, navigateToDetails)
        }
    }

    if (state.content.isEmpty()) Box(modifier = modifier.fillMaxSize()) {
        EmptyState("Articles")
    }
}