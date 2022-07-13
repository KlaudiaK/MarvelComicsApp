package com.klaudiak.marvelcomics.data.models.local


data class ComicItem(
    val id: Int,
    val title: String,
    val author: String,
    val description: String? = "",
    val thumbnail: String? = null,
    val uriDetails: String
)
