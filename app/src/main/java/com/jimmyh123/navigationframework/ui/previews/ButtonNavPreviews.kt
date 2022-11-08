package com.jimmyh123.navigationframework.ui.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jimmyh123.navigationframework.ui.presentation.*
import com.jimmyh123.navigationframework.ui.theme.NavigationFrameworkTheme

@Preview(group = "Buttons")
@Composable
fun CancelButtonEnabledPreview() {
    CancelButton(onClick = {}, enabled = true)
}

@Preview(group = "Buttons")
@Composable
fun NextButtonEnabledPreview() {
    NextPageButton(onClick = {}, enabled = true)
}

@Preview(group = "Buttons")
@Composable
fun CancelButtonDisabledPreview() {
    CancelButton(onClick = {}, enabled = false)
}

@Preview(group = "Buttons")
@Composable
fun NextButtonDisabledPreview() {
    NextPageButton(onClick = {}, enabled = false)
}

@Preview(group = "Screens")
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

@Preview(group = "Screens")
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

@Preview(group = "Screens")
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

@Preview(group = "Text")
@Composable
fun ButtonNavScreenTextPreview() {
    Surface(
        color = MaterialTheme.colors.secondary,
        modifier = Modifier.fillMaxWidth()
    ) {
        ButtonNavScreenText("Test Text")
    }
}