package com.jimmyh123.navigationframework.ui.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jimmyh123.navigationframework.ui.presentation.AddButtonNavScreenFab
import com.jimmyh123.navigationframework.ui.presentation.AddTabScreenFab
import com.jimmyh123.navigationframework.ui.presentation.FabScreenOne
import com.jimmyh123.navigationframework.ui.theme.NavigationFrameworkTheme

@Preview(group = "buttons")
@Composable
fun AddTabScreenFabPreview() {
    AddTabScreenFab(onClick = {})
}

@Preview(group = "buttons")
@Composable
fun AddButtonNavScreenFabPreview() {
    AddButtonNavScreenFab()
}

@Preview(group = "Screens")
@Composable
fun FabScreenOnePreview() {
    NavigationFrameworkTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            FabScreenOne()
        }
    }
}