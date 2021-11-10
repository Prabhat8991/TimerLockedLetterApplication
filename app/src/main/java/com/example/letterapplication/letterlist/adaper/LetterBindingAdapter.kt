package com.example.letterapplication.letterlist.adaper

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter

@BindingAdapter("lockStatus")
fun ImageView.lockStatus(isLocked: Boolean) {
    if (isLocked) {
        this.visibility = View.VISIBLE
        this.isClickable = false
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter("clickable")
fun ConstraintLayout.clickable(isLocked: Boolean) {
    this.isClickable = !isLocked
}