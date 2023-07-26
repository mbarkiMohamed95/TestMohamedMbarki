package com.example.CoTest.routing

import androidx.annotation.StringRes
import com.example.CoTest.R

/**
 * declare the used screen
 */
sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_title,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Detail :
        Screen("detail_screen", objectName = "detailItem", objectPath = "/{detailItem}")

    object HomeNav : Screen("home_screen", title = R.string.home)

}