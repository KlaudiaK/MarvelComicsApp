package com.klaudiak.marvelcomics.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.klaudiak.marvelcomics.presentation.comics_list.ComicDetail
import com.klaudiak.marvelcomics.presentation.search_screen.ComicListScreen
import com.klaudiak.marvelcomics.presentation.search_screen.ComicSearchScreen

@Composable
fun NavHostComposable() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "comic_list_screen"
    ) {
        composable("comic_list_screen") {
            ComicListScreen(navController = navController)
        }
        composable("comic_search_screen") {
            ComicSearchScreen(navController = navController)
        }
        composable(
            "comic_detail_screen/{comicId}",
            arguments = listOf(
                navArgument("comicId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("comicId")
                ?.let {
                    ComicDetail(navController = navController, id = it)
                }
        }
    }
}