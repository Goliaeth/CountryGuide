package com.example.countryguide

import android.widget.ImageView
import coil.load
import java.util.*

suspend fun loadImg(imageView: ImageView,url: String) {
    imageView.load(url)
}