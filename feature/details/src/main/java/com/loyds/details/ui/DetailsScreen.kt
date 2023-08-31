package com.loyds.details.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.loyds.details.models.DetailsUiState

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.IconButton
import com.loyds.designsystem.icons.NewsIcon.ImageIcon
import com.loyds.designsystem.icons.NewsIcons.Back
import com.loyds.designsystem.icons.toIcon
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.testTag
import com.loyds.designsystem.icons.NewsIcons.Favourite
import com.loyds.designsystem.icons.NewsIcons.FavouriteOutlined
import com.loyds.designsystem.theme.NewsTheme
import com.loyds.details.component.HTMLText
import com.loyds.details.ui.DetailsViewModel
import com.loyds.model.data.Article

@Composable
fun DetailsRoute(
    navigateBack: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    viewModel.HandleUiState() { modifier, state ->
        DetailsScreen(modifier = modifier, state = state, navigateBack, viewModel::updateFavourite )
    }
}

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    state: DetailsUiState,
    navigateBack: () -> Unit,
    onFavourite: (Article) -> Unit,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(NewsTheme.dimens.medium)
    ) {

        Box {

            AsyncImage(
                model = state.content.thumbnail,
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )

            IconButton(
                modifier = Modifier.align(Alignment.TopStart),
                onClick = navigateBack,
            ) {
                ImageIcon(Back).toIcon("back")
            }

            IconButton(
                modifier = Modifier.align(Alignment.TopEnd).testTag("Favourite_Button"),
                onClick = { onFavourite(state.content) },
            ) {
                ImageIcon(
                    if (state.content.favourite) Favourite else FavouriteOutlined
                ).toIcon("favourite")
            }
        }

        Text(
            text = state.content.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = NewsTheme.dimens.medium)
        )

        Row(
            modifier = Modifier.padding(horizontal = NewsTheme.dimens.medium)
        ) {

            Text(
                text = state.content.sectionName,
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        HTMLText(
            html = state.content.body,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = NewsTheme.dimens.medium)
        )
    }
}