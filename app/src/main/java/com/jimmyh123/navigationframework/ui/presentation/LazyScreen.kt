package com.jimmyh123.navigationframework.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jimmyh123.navigationframework.model.PhotoDetail

@Composable
fun PhotoGrid(
    photoList: List<PhotoDetail>,
    onPhotoCardClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(4.dp),
        columns = GridCells.Adaptive(minSize = 128.dp),
    ) {
        items(photoList.size) { photo ->
            PhotoCard(photoList[photo], onPhotoCardClicked)
        }
    }
}

@Composable
fun PhotoCard(
    photo: PhotoDetail,
    onclick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable(
                enabled = true,
                onClick = onclick
            ),
        elevation = 4.dp
    ){
        Column {
            Image(
                painter = painterResource(id = photo.imageResourceId),
                contentDescription = stringResource(id = photo.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            PhotoText(photo.stringResourceId)
        }
    }
}

@Composable
fun PhotoText(
    text: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = text),
        modifier = Modifier.padding(16.dp),
    )
}