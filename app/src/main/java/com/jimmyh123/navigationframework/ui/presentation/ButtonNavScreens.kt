package com.jimmyh123.navigationframework.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jimmyh123.navigationframework.ui.theme.NavigationFrameworkTheme

@Composable
fun StartScreen(
    onNextButtonClicked: () -> Unit
) {
    Column(){
        Text("Start Screen")
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            NextPageButton(onNextButtonClicked)
        }
    }
}


@Composable
fun MiddleScreen(
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    Column(){
        Text("Middle Screen")
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            NextPageButton(onNextButtonClicked)
            CancelButton(onCancelButtonClicked)
        }
    }
}

@Composable
fun EndScreen(onCancelButtonClicked: () -> Unit) {
    Column(){
        Text("End Screen")
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            CancelButton(onCancelButtonClicked)
        }
    }
}

@Composable
fun NextPageButton(onClick: () -> Unit) {

    Button(
        onClick = onClick,
    ){
        Text("Next")
    }
}

@Composable
fun CancelButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
    ){
        Text("Cancel")
    }
}


@Preview
@Composable
fun PreviewStartScreen() {

    NavigationFrameworkTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            StartScreen {  }
        }
    }
}

@Preview
@Composable
fun PreviewMiddleScreen() {

    NavigationFrameworkTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
//            MiddleScreen{}
        }
    }
}

@Preview
@Composable
fun PreviewEndScreen() {

    NavigationFrameworkTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            EndScreen{}
        }
    }
}