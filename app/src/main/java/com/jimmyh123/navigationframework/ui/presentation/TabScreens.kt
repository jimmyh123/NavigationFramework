package com.jimmyh123.navigationframework.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jimmyh123.navigationframework.R
import com.jimmyh123.navigationframework.ui.theme.NavigationFrameworkTheme

@Composable
fun TabScreenOne() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        val tabTextModifier = Modifier.align(Alignment.CenterHorizontally)
        TabScreenText("TabScreenOne!", tabTextModifier)
    }
}

@Composable
fun TabScreenTwo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_200))
            .wrapContentSize(Alignment.Center)
    ) {
        val tabTextModifier = Modifier.align(Alignment.CenterHorizontally)
        TabScreenText("TabScreenTwo!", tabTextModifier)
    }
}

@Composable
fun TabScreenThree() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
            .wrapContentSize(Alignment.Center)
    ) {
        val tabTextModifier = Modifier.align(Alignment.CenterHorizontally)
        TabScreenText("TabScreenThree!", tabTextModifier)
    }
}

@Composable
fun TabScreenText(
    tabScreenTitleText: String,
    modifier: Modifier
) {
    Text(
        text = tabScreenTitleText,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 25.sp
    )
}

@Composable
fun AddFab() {
    FloatingActionButton(
        onClick = { /*do something*/ }
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Localized description")
    }
}


@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    NavigationFrameworkTheme {
        TabScreenOne()
    }
}