package com.jimmyh123.navigationframework.ui.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jimmyh123.navigationframework.data.PhotoDatasource
import com.jimmyh123.navigationframework.ui.presentation.PhotoCard
import com.jimmyh123.navigationframework.ui.presentation.PhotoGrid
import com.jimmyh123.navigationframework.ui.presentation.PhotoText
import com.jimmyh123.navigationframework.ui.theme.NavigationFrameworkTheme

@Preview
@Composable
fun PhotoGridPreview() {
    PhotoGrid(
        photoList = PhotoDatasource().loadPhotos().subList(0,4),
        onPhotoCardClicked = {}
    )
}

@Preview(group = "PhotoCard")
@Composable
fun PhotoCardPreview() {
    PhotoCard(photo = PhotoDatasource().loadPhotos()[0], onclick = {})
}

@Preview(group = "Text")
@Composable
fun PhotoTextPreview() {
    NavigationFrameworkTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxWidth()
        ) {
            PhotoText(PhotoDatasource().loadPhotos()[0].stringResourceId)
        }
    }
}