package com.loyds.news.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.loyds.articles.navigation.articlesGraphRoutePattern
import com.loyds.articles.navigation.articlesGraph
import com.loyds.favourites.navigation.favouritesGraph
import com.loyds.details.navigation.detailsGraph
import com.loyds.details.navigation.navigateToDetails
import com.loyds.profile.navigation.profileGraph

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = articlesGraphRoutePattern,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        articlesGraph(
            navigateToDetails = navController::navigateToDetails
        )
        favouritesGraph(
            navigateToDetails = navController::navigateToDetails
        )
        profileGraph(

        )
        detailsGraph(
            navigateBack = navController::popBackStack
        )
    }
}