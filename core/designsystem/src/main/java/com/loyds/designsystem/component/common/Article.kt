package com.loyds.designsystem.component.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.loyds.designsystem.theme.NewsTheme
import com.loyds.model.data.Article

@Composable
fun Article(article: Article, navigateToDetails: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { navigateToDetails(article.id) })
    ) {

        AsyncImage(
            model = article.thumbnail,
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(160.dp)
                .clip(RoundedCornerShape(NewsTheme.dimens.medium))
        )

        Column(modifier = Modifier.padding(start = NewsTheme.dimens.medium)) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 3
            )

            Row {
                Text(
                    text = article.sectionName,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}