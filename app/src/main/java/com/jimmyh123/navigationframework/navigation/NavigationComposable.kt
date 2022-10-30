package com.jimmyh123.navigationframework.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jimmyh123.navigationframework.ui.MainViewModel
import com.jimmyh123.navigationframework.ui.presentation.EndScreen
import com.jimmyh123.navigationframework.ui.presentation.MiddleScreen
import com.jimmyh123.navigationframework.ui.presentation.StartScreen

enum class BaseNavigationLocations(){
    Start,
    Middle,
    End
}

@Composable
fun TopBarComposable(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    currentScreen: BaseNavigationLocations,
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
    val backStackEntry by navController.currentBackStackEntryAsState()
    val uiState by mainViewModel.uiState.collectAsState()

    val currentScreen = BaseNavigationLocations.valueOf(
        backStackEntry?.destination?.route ?: BaseNavigationLocations.Start.name
    )
    Scaffold(
        topBar = {
            TopBarComposable(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                currentScreen = currentScreen
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BaseNavigationLocations.Start.name,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = BaseNavigationLocations.Start.name){
                StartScreen( onNextButtonClicked = { navController.navigate(BaseNavigationLocations.Middle.name) } )
            }
            composable(route = BaseNavigationLocations.Middle.name) {
                MiddleScreen(
                    onNextButtonClicked = { navController.navigate(BaseNavigationLocations.End.name) },
                    onCancelButtonClicked = { navigateBackToStart(navController) }
                )
            }
            composable(route = BaseNavigationLocations.End.name){
                EndScreen(onCancelButtonClicked = { navigateBackToStart(navController) })
            }
        }
    }
}

private fun navigateBackToStart(
    navController: NavHostController
){
    navController.popBackStack(BaseNavigationLocations.Start.name,inclusive = false)
}
