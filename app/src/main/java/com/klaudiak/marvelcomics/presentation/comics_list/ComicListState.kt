package com.klaudiak.marvelcomics.presentation.comics_list

import com.klaudiak.marvelcomics.data.models.local.ComicItem


data class ComicListState(
    val items: List<ComicItem>? = listOf(),
    val searchedItems: List<ComicItem>? = listOf(),
    val comic: ComicItem? = null,
    val query: String = "",
    val loadError: String? = "",
    val noResults: Boolean = false,
    val isLoading: Boolean = true
)