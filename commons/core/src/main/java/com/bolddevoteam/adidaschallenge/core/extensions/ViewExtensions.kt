package com.bolddevoteam.adidaschallenge.core.extensions

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.onClick( func : () -> Unit) = this.setOnClickListener{ func() }

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun ImageView.loadImage(url: String, placeholder: Drawable, error: Drawable) = Glide.with(this)
    .load(url)
    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    .placeholder(placeholder)
    .error(error)
    .into(this)

fun ImageView.loadImage(url: String) = Glide.with(this)
    .load(url)
    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    .into(this)
