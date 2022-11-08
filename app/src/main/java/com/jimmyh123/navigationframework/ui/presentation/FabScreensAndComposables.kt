package com.jimmyh123.navigationframework.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.jimmyh123.navigationframework.ui.theme.Pumpkin

@Composable
fun AddTabScreenFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Localized description")
    }
}

@Composable
fun AddButtonNavScreenFab() {
    ExtendedFloatingActionButton(
        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
        text = { Text("ADD TO FAVOURITES") },
        onClick = { /*do something*/ }
    )
}

@Composable
fun FabScreenOne() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(Pumpkin.toArgb()))
            .wrapContentSize(Alignment.Center)
    ) {
        val tabTextModifier = Modifier.align(Alignment.CenterHorizontally)
        TabScreenText("FabScreenOne!", tabTextModifier)
    }
}