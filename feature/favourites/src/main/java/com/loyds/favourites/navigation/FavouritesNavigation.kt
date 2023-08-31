package com.loyds.favourites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

import com.loyds.favourites.ui.favourites.FavouritesRoute
import androidx.navigation.navigation


const val favouritesGraphRoutePattern = "favourites_graph"
const val favouritesNavigationRoute = "favourites_route"


fun NavController.navigateToFavourites(navOptions: NavOptions? = null) {
    this.navigate(favouritesGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.favouritesGraph(
    navigateToDetails: (String) -> Unit,
) {
    navigation(
        route = favouritesGraphRoutePattern,
        startDestination = favouritesNavigationRoute
    ) {

        composable(route = favouritesNavigationRoute) {
            FavouritesRoute(
                 navigateToDetails = navigateToDetails,
            )
        }
    }
}