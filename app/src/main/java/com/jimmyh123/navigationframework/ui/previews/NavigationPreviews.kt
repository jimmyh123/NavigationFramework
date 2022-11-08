package com.jimmyh123.navigationframework.ui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.jimmyh123.navigationframework.navigation.*

@Preview
@Composable
fun TopBarComposablePreview() {
    TopBarComposable(canNavigateBack = true, navigateUp = {},currentScreen = ScreenNavigationLocations.Start)
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun TabsPreview() {
    val tabs = listOf(TabItem.TabScreenOne, TabItem.TabScreenTwo, TabItem.TabScreenThree)
    Tabs(tabs = tabs, pagerState = rememberPagerState())
}

@Preview
@Composable
fun NavigationComposablePreview() {
    NavigationComposable(mainViewModel = viewModel())
}