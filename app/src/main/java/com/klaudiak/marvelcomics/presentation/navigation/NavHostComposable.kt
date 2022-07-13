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
        startDestination = Screen.ComicList.route
    ) {
        composable(Screen.ComicList.route) {
            ComicListScreen(navController = navController)
        }
        composable(Screen.Search.route) {
            ComicSearchScreen(navController = navController)
        }
        composable(
            Screen.Detail.route,
            arguments = listOf(
                navArgument(Screen.COMIC_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(Screen.COMIC_ID)
                ?.let {
                    ComicDetail(navController = navController, id = it)
                }
        }
    }
}