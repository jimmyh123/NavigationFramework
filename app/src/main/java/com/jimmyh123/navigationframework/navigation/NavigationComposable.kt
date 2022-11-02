package com.jimmyh123.navigationframework.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            BottomNavigation (
                modifier = modifier,
                elevation = 2.dp
                    ){
                val currentDestination = navBackStackEntry?.destination
                items.forEach{ screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    BottomNavigationItem(
//                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        icon = {
                               Column(horizontalAlignment = CenterHorizontally){
                                   if(screen.badgeCount > 0) {
                                        BadgedBox(
                                            badge = {
                                                Text(screen.badgeCount.toString())
                                            }
                                        ) {
                                           Icon(imageVector = screen.icon, contentDescription = screen.route)
                                        }
                                   } else {
                                       Icon(imageVector = screen.icon, contentDescription = screen.route)
                                   }
                                   if (selected){
                                       Text(
                                           text = screen.route,
                                           textAlign = TextAlign.Center,
                                           fontSize = 10.sp
                                       )
                                   }
                               }
                        },
                        selected = selected,
                        selectedContentColor = Color.Yellow,
                        unselectedContentColor = Color.Gray,
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
sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    val badgeCount: Int = 0
) {
    object SectionOne : Screen("SectionOne", R.string.section_one,Icons.Default.Home,0)
    object SectionTwo : Screen("SectionTwo", R.string.section_two, Icons.Default.ShoppingCart, 5)
    object SectionThree : Screen("SectionThree", R.string.section_three, Icons.Default.Favorite,0)
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