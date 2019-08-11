package com.example.dunzomodule.views.viewutil

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.dunzomodule.R


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Log.e("DunzoImageUrl", "Image - $url")
    /*if (url == null) {
        Glide.with(view.context).load(R.drawable.default_image).into(view)
    }*/
    //url?.let {
    Glide.with(view.context)
        .load(url)
        .placeholder(R.drawable.default_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .fitCenter()
        .into(view)
    //}
}
