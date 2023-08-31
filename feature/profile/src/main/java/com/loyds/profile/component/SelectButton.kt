package com.loyds.profile.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.loyds.designsystem.icons.NewsIcons
import com.loyds.designsystem.theme.NewsTheme

@Composable
fun SelectButton(
    text: String,
    selected: Boolean = false,
    onClick: () -> Unit,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(NewsTheme.dimens.small),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(vertical = NewsTheme.dimens.medium, horizontal = NewsTheme.dimens.large),
    ) {

        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = NewsTheme.dimens.extraSmall)
        )

        if (selected) {
            Icon(
                imageVector = NewsIcons.Check,
                contentDescription = null
            )
        }
    }
}