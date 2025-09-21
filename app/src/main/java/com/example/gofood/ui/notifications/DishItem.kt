package com.example.gofood.ui.notifications

import android.R

data class DishItem(
    val title: String,
    val description: String,
    val item: String,
    val time: String,
    val category: String,
    val imageResId: Int
) {
    constructor() : this(
        title = "",
        description = "",
        item = "",
        time = "",
        category = "",
        imageResId = R.drawable.ic_menu_camera
    )
}