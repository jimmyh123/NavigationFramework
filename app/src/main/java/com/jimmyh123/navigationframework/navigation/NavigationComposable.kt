package com.jimmyh123.navigationframework.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
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
import com.google.accompanist.pager.*
import com.jimmyh123.navigationframework.ui.MainViewModel
//import com.jimmyh123.navigationframework.ui.presentation.bottom_bar_one.EndScreen
//import com.jimmyh123.navigationframework.ui.presentation.bottom_bar_one.MiddleScreen
//import com.jimmyh123.navigationframework.ui.presentation.bottom_bar_one.StartScreen
import com.jimmyh123.navigationframework.R
import com.jimmyh123.navigationframework.data.PhotoDatasource
import com.jimmyh123.navigationframework.ui.presentation.*
import kotlinx.coroutines.launch

private const val TAG = "NavigationComposable"

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

@OptIn(ExperimentalPagerApi::class)
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

    // tabs
    val tabs = listOf(TabItem.TabScreenOne, TabItem.TabScreenTwo, TabItem.TabScreenThree)
    val pagerState = rememberPagerState()

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
        },
        floatingActionButton = {
            val currentDestination = navBackStackEntry?.destination
            val sectionTwoDestinations = listOf("SectionTwo","Start","Middle","End")
//            Log.i(TAG,currentDestination.toString())
            if (currentDestination?.route==Screen.SectionOne.route){
                AddTabScreenFab(onClick = { navController.navigate(ScreenNavigationLocations.fabScreenOne.name) })
            }
            if (currentDestination?.route in sectionTwoDestinations){AddButtonNavScreenFab()}
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.SectionOne.route,
            modifier = modifier.padding(innerPadding)
        ){

            // bottom bar item 1 -
            composable(Screen.SectionOne.route) {
                Column() {
                    Tabs(tabs = tabs, pagerState = pagerState)
                    TabsContent(tabs = tabs, pagerState = pagerState)
                }
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
                StartScreen( onNextButtonClicked = { navController.navigate(ScreenNavigationLocations.Middle.name) } )            }

            // bottom bar item 3
            composable(Screen.SectionThree.route) {
//                EndScreen(onCancelButtonClicked = { navigateBackToStart(navController) })
                PhotoGrid(photoList = PhotoDatasource().loadPhotos())
            }

            // fab navigation 1
            composable(ScreenNavigationLocations.fabScreenOne.name) {
                FabScreenOne()
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = colorResource(id = R.color.purple_500),
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                icon = { Icon(imageVector = tab.icon, contentDescription = tab.title) },
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
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
    object SectionTwo : Screen("SectionTwo", R.string.section_two, Icons.Default.Favorite, 0)
    object SectionThree : Screen("SectionThree", R.string.section_three, Icons.Default.ShoppingCart,12)
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
    End,
    fabScreenOne
}

// define tab navigation
typealias ComposableFun = @Composable () -> Unit
sealed class TabItem(val icon: ImageVector, var title: String, var screen: ComposableFun) {
    object TabScreenOne : TabItem(Icons.Default.Email, "Tab One", { TabScreenOne() })
    object TabScreenTwo : TabItem(Icons.Default.Edit, "Tab Two", { TabScreenTwo() })
    object TabScreenThree : TabItem(Icons.Default.Call, "Tab Three", { TabScreenThree() })
}

