package com.loyds.news.navigation

import com.loyds.designsystem.icons.NewsIcon
import com.loyds.designsystem.icons.NewsIcon.DrawableIcon
import com.loyds.designsystem.icons.NewsIcon.ImageIcon
import com.loyds.designsystem.icons.NewsIcons
import com.loyds.designsystem.R
import com.loyds.articles.navigation.articlesGraphRoutePattern
import com.loyds.favourites.navigation.favouritesGraphRoutePattern
import com.loyds.profile.navigation.profileGraphRoutePattern

enum class TopLevelDestination(
    val icon: NewsIcon,
    val iconTextId: Int,
    val route: String,
) {
    ARTICLES(
        icon = ImageIcon(NewsIcons.Home),
        iconTextId = R.string.articles_title,
        route = articlesGraphRoutePattern
    ),
    FAVOURITES(
        icon = ImageIcon(NewsIcons.FavouriteOutlined),
        iconTextId = R.string.favourites_title,
        route = favouritesGraphRoutePattern
    ),
    SETTINGS(
        icon = ImageIcon(NewsIcons.Settings),
        iconTextId = R.string.settings_title,
        route = profileGraphRoutePattern
    ),
}