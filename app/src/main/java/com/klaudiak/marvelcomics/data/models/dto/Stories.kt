package com.klaudiak.marvelcomics.data.models.dto

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)