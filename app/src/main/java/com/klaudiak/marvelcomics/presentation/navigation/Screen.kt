package com.klaudiak.marvelcomics.presentation.navigation

sealed class Screen(val route: String) {
    object ComicList : Screen("comic_list_screen")
    object Search : Screen("comic_search_screen")
    object Detail : Screen("comic_detail_screen/{$COMIC_ID}")

    companion object {
        const val COMIC_ID = "comicId"
    }

}