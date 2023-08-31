package com.loyds.designsystem.icons

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

object NewsIcons {
    val Home = Icons.Outlined.Home
    val Back = Icons.Filled.ArrowBack
    val Favourite = Icons.Outlined.Favorite
    val FavouriteOutlined = Icons.Outlined.FavoriteBorder
    val Settings = Icons.Outlined.Settings
    val Check = Icons.Outlined.Check
}

sealed class NewsIcon {
    data class ImageIcon(val imageVector: ImageVector) : NewsIcon()
    data class DrawableIcon(@DrawableRes val id: Int) : NewsIcon()
}

@Composable
fun NewsIcon.toIcon(
    contentDescription: String = ""
) {
    when (this) {
        is NewsIcon.ImageIcon -> Icon(
            imageVector = this.imageVector,
            contentDescription = contentDescription,
        )
        is NewsIcon.DrawableIcon -> Icon(
            painter = painterResource(id = this.id),
            contentDescription = contentDescription,
        )
    }
}