package com.klaudiak.marvelcomics.presentation.bar


sealed class Screen(val route: String) {
    object ComicList : Screen("comic_list_screen")
    object Search : Screen("comic_search_screen")
}