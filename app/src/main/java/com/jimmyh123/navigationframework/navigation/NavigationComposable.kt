package com.jimmyh123.navigationframework.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jimmyh123.navigationframework.ui.MainViewModel
import com.jimmyh123.navigationframework.ui.presentation.bottom_bar_one.EndScreen
import com.jimmyh123.navigationframework.ui.presentation.bottom_bar_one.MiddleScreen
import com.jimmyh123.navigationframework.ui.presentation.bottom_bar_one.StartScreen
import com.jimmyh123.navigationframework.R

@Composable
fun TopBarComposable(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    currentScreen: ScreenNavigationLocations
) {
    TopAppBar(
        title = { Text(currentScreen.name) },
//        title = { Text("Screen name") },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back button"
                    )
                }
            }
        }
    )
}

@Composable
fun NavigationComposable(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()
    val uiState by mainViewModel.uiState.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState() 
    val currentScreen = ScreenNavigationLocations.valueOf(
    navBackStackEntry?.destination?.route ?: ScreenNavigationLocations.SectionOne.name
    )

    Scaffold(
        topBar = {
            TopBarComposable(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                currentScreen = currentScreen
            )
        },
        bottomBar = {
            BottomNavigation {

                val currentDestination = navBackStackEntry?.destination
                items.forEach{ screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // pop up to start destination of the graph
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                // avoid multiple copies when reselecting same item
                                launchSingleTop = true
                                // restore state when reselecting previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.SectionOne.route,
            modifier = modifier.padding(innerPadding)
        ){

            // bottom bar item 1 -
            composable(Screen.SectionOne.route) {
                StartScreen( onNextButtonClicked = { navController.navigate(ScreenNavigationLocations.Middle.name) } )
            }
            composable(route = ScreenNavigationLocations.Middle.name) {
                MiddleScreen(
                    onNextButtonClicked = { navController.navigate(ScreenNavigationLocations.End.name) },
                    onCancelButtonClicked = { navigateBackToStart(navController) }
                )
            }
            composable(route = ScreenNavigationLocations.End.name){
                EndScreen(onCancelButtonClicked = { navigateBackToStart(navController) })
            }

            // bottom bar item 2
            composable(Screen.SectionTwo.route) {
                EndScreen(onCancelButtonClicked = { navigateBackToStart(navController) })            }

            // bottom bar item 3
            composable(Screen.SectionThree.route) {
                EndScreen(onCancelButtonClicked = { navigateBackToStart(navController) })
            }
        }
    }
}

private fun navigateBackToStart(
    navController: NavHostController
){
    navController.popBackStack(Screen.SectionOne.route,inclusive = false)
}

// define bottom bar navigation
sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object SectionOne : Screen("SectionOne", R.string.section_one)
    object SectionTwo : Screen("SectionTwo", R.string.section_two)
    object SectionThree : Screen("SectionThree", R.string.section_three)
}

val items = listOf(
    Screen.SectionOne,
    Screen.SectionTwo,
    Screen.SectionThree,
)

enum class ScreenNavigationLocations(){
    SectionOne,
    SectionTwo,
    SectionThree,
    Start,
    Middle,
    End
}