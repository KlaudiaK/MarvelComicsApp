package com.klaudiak.marvelcomics.data.models.dto

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)