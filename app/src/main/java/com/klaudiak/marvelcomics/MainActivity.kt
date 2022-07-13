package com.klaudiak.marvelcomics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.klaudiak.marvelcomics.data.ComicApi
import com.klaudiak.marvelcomics.presentation.comics_list.ComicDetail
import com.klaudiak.marvelcomics.presentation.search_screen.ComicListScreen
import com.klaudiak.marvelcomics.presentation.search_screen.ComicSearchScreen
import com.klaudiak.marvelcomics.domain.ComicRepository
import com.klaudiak.marvelcomics.presentation.navigation.NavHostComposable
import com.klaudiak.marvelcomics.ui.theme.MarvelComicsTheme

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val value = 6
        setContent {
            MarvelComicsTheme(darkTheme = false, content = {
                NavHostComposable()
            })
        }
    }
}


