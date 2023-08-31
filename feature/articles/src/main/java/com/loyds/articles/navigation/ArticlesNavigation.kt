package com.loyds.articles.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

import com.loyds.articles.ui.articles.ArticlesRoute
import androidx.navigation.navigation


const val articlesGraphRoutePattern = "articles_graph"
const val articlesNavigationRoute = "articles_route"


fun NavController.navigateToArticles(navOptions: NavOptions? = null) {
    this.navigate(articlesGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.articlesGraph(
    navigateToDetails: (String) -> Unit,
) {
    navigation(
        route = articlesGraphRoutePattern,
        startDestination = articlesNavigationRoute
    ) {

        composable(route = articlesNavigationRoute) {
            ArticlesRoute(
                 navigateToDetails = navigateToDetails,
            )
        }
    }
}