package com.example.dunzomodule.utils

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Log.i("DunzoImageUrl", "Image - $url")
    val context = view.getContext()
    Glide.with(context).load(url).into(view)
}
