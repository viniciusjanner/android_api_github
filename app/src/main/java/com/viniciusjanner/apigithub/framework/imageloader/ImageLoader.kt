package com.viniciusjanner.apigithub.framework.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.viniciusjanner.apigithub.R

interface ImageLoader {

    fun load(
        imageView: ImageView,
        imageUrl: String,
        @DrawableRes placeholder: Int = R.drawable.ic_person,
        @DrawableRes fallback: Int = R.drawable.ic_error,
    )
}
