package com.jimmyh123.navigationframework.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jimmyh123.navigationframework.ui.theme.NavigationFrameworkTheme

@Composable
fun MiddleScreen(
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    Column(){
        Text("Middle Screen")
        NextPageButton(onNextButtonClicked)
        CancelButton(onCancelButtonClicked)
    }
}


@Preview
@Composable
fun previewMiddleScreen() {

    NavigationFrameworkTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
//            MiddleScreen{}
        }
    }
}