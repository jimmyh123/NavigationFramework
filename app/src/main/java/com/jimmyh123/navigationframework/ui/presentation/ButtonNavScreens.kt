package com.jimmyh123.navigationframework.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jimmyh123.navigationframework.ui.theme.Desire
import com.jimmyh123.navigationframework.ui.theme.NavigationFrameworkTheme

@Composable
fun StartScreen(
    onNextButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(Desire.toArgb())),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        val screenTextModifier = Modifier.align(Alignment.CenterHorizontally)
        ButtonNavScreenText("StartScreen",screenTextModifier)

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            CancelButton(onClick = {}, false)
            NextPageButton(onNextButtonClicked, true)
        }
    }
}


@Composable
fun MiddleScreen(
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(Desire.toArgb())),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        val screenTextModifier = Modifier.align(Alignment.CenterHorizontally)
        ButtonNavScreenText("MiddleScreen",screenTextModifier)

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            CancelButton(onCancelButtonClicked, true)
            NextPageButton(onNextButtonClicked, true)
        }
    }
}

@Composable
fun EndScreen(onCancelButtonClicked: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(Desire.toArgb())),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        val screenTextModifier = Modifier.align(Alignment.CenterHorizontally)
        ButtonNavScreenText("EndScreen",screenTextModifier)

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            CancelButton(onCancelButtonClicked, true)
            NextPageButton(onClick = {}, false)
        }
    }
}

@Composable
fun ButtonNavScreenText(screenTitleText: String, modifier: Modifier) {
    Text(
        text = screenTitleText,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 25.sp
    )
}


@Composable
fun NextPageButton(
    onClick: () -> Unit,
    enabled: Boolean
) {

    Button(
        onClick = onClick,
        enabled = enabled
    ){
        Text("Next")
    }
}

@Composable
fun CancelButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        onClick = onClick,
        enabled = enabled
    ){
        Text("Cancel")
    }
}


@Preview
@Composable
fun StartScreenPreview() {

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
fun MiddleScreenPreview() {

    NavigationFrameworkTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MiddleScreen(onNextButtonClicked = {}, onCancelButtonClicked = {})
        }
    }
}

@Preview
@Composable
fun EndScreenPreview() {

    NavigationFrameworkTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            EndScreen{}
        }
    }
}