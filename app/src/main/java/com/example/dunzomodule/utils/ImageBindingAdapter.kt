package com.example.dunzomodule.utils

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Log.i("DunzoImageUrl", "Image - $url")
    url?.let {
        Glide.with(view.context).load(url).into(view)
    }
}
