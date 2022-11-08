package com.jimmyh123.navigationframework.ui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jimmyh123.navigationframework.ui.presentation.TabScreenOne
import com.jimmyh123.navigationframework.ui.presentation.TabScreenThree
import com.jimmyh123.navigationframework.ui.presentation.TabScreenTwo
import com.jimmyh123.navigationframework.ui.theme.NavigationFrameworkTheme

@Preview(group = "Screens", showBackground = true)
@Composable
fun TabScreenOnePreview() {
    NavigationFrameworkTheme {
        TabScreenOne()
    }
}

@Preview(group = "Screens", showBackground = true)
@Composable
fun TabScreenTwoPreview() {
    NavigationFrameworkTheme {
        TabScreenTwo()
    }
}

@Preview(group = "Screens", showBackground = true)
@Composable
fun TabScreenThreePreview() {
    NavigationFrameworkTheme {
        TabScreenThree()
    }
}