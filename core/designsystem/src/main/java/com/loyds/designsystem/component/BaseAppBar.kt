package com.loyds.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.loyds.designsystem.icons.NewsIcon.ImageIcon
import com.loyds.designsystem.icons.NewsIcons.Back
import com.loyds.designsystem.icons.toIcon

@Composable
fun BaseAppBar(
    @StringRes title: Int,
    backAction: (() -> Unit)
) {
    BaseAppBar(title = stringResource(id = title), backAction = backAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppBar(
    title: String,
    backAction: (() -> Unit)
) {
    TopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.headlineSmall) },
        navigationIcon = {
            IconButton(
                onClick = backAction
            ) {
                ImageIcon(Back).toIcon("back")
            }
        }
    )
}