package com.loyds.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

import com.loyds.settings.ui.settings.SettingsRoute
import androidx.navigation.navigation


const val settingsGraphRoutePattern = "settings_graph"
const val settingsNavigationRoute = "settings_route"


fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(settingsGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.settingsGraph(

) {
    navigation(
        route = settingsGraphRoutePattern,
        startDestination = settingsNavigationRoute
    ) {

        composable(route = settingsNavigationRoute) {
            SettingsRoute(
    
            )
        }
    }
}