package com.klaudiak.marvelcomics.presentation.search_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klaudiak.marvelcomics.R
import com.klaudiak.marvelcomics.data.models.local.ComicItem
import com.klaudiak.marvelcomics.presentation.comics_list.ComicListViewModel
import com.klaudiak.marvelcomics.presentation.bar.BottomNavigationBar
import com.klaudiak.marvelcomics.presentation.bar.TopAppBar
import com.klaudiak.marvelcomics.presentation.comics_list.ComicCard
import com.klaudiak.marvelcomics.ui.theme.White40


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComicListScreen(
    navController: NavController,
    viewModel: ComicListViewModel = hiltViewModel<ComicListViewModel>()
) {

    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            TopAppBar(navController = navController, text = stringResource(R.string.marvel_comics))
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        contentColor = White40
    ) {
        ComicList(comicList = uiState.value.items, navController = navController)
    }

}

@Composable
fun ComicList(
    navController: NavController,
    comicList: List<ComicItem>? = listOf()
) {

    LazyColumn(
        modifier = Modifier.padding(top = 60.dp, bottom = 40.dp),
        content = {
            comicList.let {
                if (it != null) {
                    items(it.size) { i ->
                        val comic = comicList?.get(i)
                        if (comic != null) {
                            ComicCard(comic = comic, navController = navController)
                        }
                    }
                }
            }

        })

}






