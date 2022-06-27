package com.klaudiak.marvelcomics.presentation.bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = Screen.ComicList.route,
        icon = Icons.Default.Home
    )

    object Search : BottomNavItem(
        route = Screen.Search.route,
        icon = Icons.Default.Search
    )
}