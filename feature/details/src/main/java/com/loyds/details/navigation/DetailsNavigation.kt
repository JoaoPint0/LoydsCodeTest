package com.loyds.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

import com.loyds.details.ui.details.DetailsRoute
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument


const val detailsNavigationRoute = "details_route"
const val detailsIdArg = "detailsId"

class DetailsArgs(val id: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(Uri.decode(checkNotNull(savedStateHandle[detailsIdArg])))
}


fun NavController.navigateToDetails(detailsId: String, navOptions: NavOptions? = null) {
    val encodedId = Uri.encode(detailsId)
    this.navigate("$detailsNavigationRoute/$encodedId", navOptions)
}
fun NavController.navigateToDetails(navOptions: NavOptions? = null) {
    this.navigate("$detailsNavigationRoute", navOptions)
}

fun NavGraphBuilder.detailsGraph(
    navigateBack: () -> Unit,
) {
    
        composable(
            route = "details_route/{$detailsIdArg}",
            arguments = listOf(
                navArgument(detailsIdArg) { type = NavType.StringType }
            )
        ) {
            DetailsRoute(navigateBack)
        }
}