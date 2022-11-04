package com.jimmyh123.navigationframework.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PhotoDetail(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
