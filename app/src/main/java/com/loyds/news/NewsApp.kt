package com.loyds.news

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.loyds.data.model.Theme
import com.loyds.designsystem.component.NewsNavigationBar
import com.loyds.designsystem.component.NewsNavigationBarItem
import com.loyds.designsystem.component.NewsNavigationRail
import com.loyds.designsystem.component.NewsNavigationRailItem
import com.loyds.designsystem.icons.NewsIcon
import com.loyds.designsystem.theme.NewsTheme
import com.loyds.news.navigation.TopLevelDestination
import com.loyds.news.navigation.MainNavHost
import com.loyds.news.navigation.AppState
import com.loyds.news.navigation.rememberAppState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NewsApp(
    windowSizeClass: WindowSizeClass,
    appState: AppState = rememberAppState(windowSizeClass),
    viewModel: MainViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    state.HandleUiState(modifier = Modifier, refreshClicked = { }) { _, state ->

        NewsTheme(
            darkTheme = when (state.theme) {
                Theme.DARK -> true
                Theme.LIGHT -> false
                Theme.SYSTEM -> isSystemInDarkTheme()
            },
            dynamicColor = state.dynamic
        ) {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                MainNavHost(
                    navController = appState.navController,
                    modifier = Modifier
                )
            }

            val snackbarHostState = remember { SnackbarHostState() }

            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                bottomBar = {
                    if (appState.shouldShowBottomBar) {
                        NewsBottomBar(
                            destinations = appState.topLevelDestinations,
                            onNavigateToDestination = appState::navigateToTopLevelDestination,
                            currentDestination = appState.currentDestination,
                            modifier = Modifier.testTag("NewsBottomBar")
                        )
                    }
                }
            ) { padding ->
                if (appState.shouldShowNavRail) {
                    NewsNavRail(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                        modifier = Modifier
                            .testTag("NewsNavRail")
                            .safeDrawingPadding()
                    )
                }

                MainNavHost(
                    navController = appState.navController,
                    modifier = Modifier
                        .padding(padding)
                        .consumeWindowInsets(padding)
                )
            }
        }
    }
}

@Composable
private fun NewsNavRail(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NewsNavigationRail(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NewsNavigationRailItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    when (val icon = destination.icon) {
                        is NewsIcon.ImageIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null
                        )

                        is NewsIcon.DrawableIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null
                        )
                    }
                },
                label = { Text(stringResource(destination.iconTextId)) }
            )
        }
    }
}

@Composable
private fun NewsBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NewsNavigationBar(
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NewsNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    when (val icon = destination.icon) {
                        is NewsIcon.ImageIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null
                        )

                        is NewsIcon.DrawableIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null
                        )
                    }
                },
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false