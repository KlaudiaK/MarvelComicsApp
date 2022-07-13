package com.klaudiak.marvelcomics.presentation.comics_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klaudiak.marvelcomics.R
import com.klaudiak.marvelcomics.presentation.bar.BottomNavigationBar
import com.klaudiak.marvelcomics.presentation.bar.TopAppBar
import com.klaudiak.marvelcomics.presentation.comic_detail.BottomSheet


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ComicDetail(
    navController: NavController,
    id: String,
    viewModel: ComicListViewModel = hiltViewModel<ComicListViewModel>()
) {
    val uiState = viewModel.uiState
    viewModel.getComicById(id)
    val comic = uiState.value.comic

    Scaffold(
        topBar = { TopAppBar(navController = navController, text = stringResource(id = R.string.details)) },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        if (comic != null) {
            BottomSheet(comic = comic)
        }
    }

}
