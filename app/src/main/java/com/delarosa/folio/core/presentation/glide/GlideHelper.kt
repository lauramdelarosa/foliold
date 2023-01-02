package com.delarosa.folio.core.presentation.glide

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImageFromUrl(url: String) {
    val requestOptions: RequestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .skipMemoryCache(true)
        .centerCrop()
        .dontAnimate()
        .dontTransform()
        .encodeFormat(Bitmap.CompressFormat.PNG)
        .format(DecodeFormat.DEFAULT)
    Glide.with(this)
        .applyDefaultRequestOptions(requestOptions)
        .load(url)
        .thumbnail(0.05f)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.loadImageFromPathWithBaseUrl(path: String) {
    this.loadImageFromUrl(path)
}
