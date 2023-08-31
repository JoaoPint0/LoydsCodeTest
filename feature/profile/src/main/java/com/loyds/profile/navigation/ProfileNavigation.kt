package com.loyds.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

import com.loyds.profile.ui.ThemeRoute
import androidx.navigation.navigation


const val profileGraphRoutePattern = "profile_graph"
const val settingsNavigationRoute = "settings_route"


fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(profileGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.profileGraph(

) {
    navigation(
        route = profileGraphRoutePattern,
        startDestination = settingsNavigationRoute
    ) {

        composable(route = settingsNavigationRoute) {
            ThemeRoute(
    
            )
        }
    }
}