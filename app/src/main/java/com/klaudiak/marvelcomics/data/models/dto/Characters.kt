package com.klaudiak.marvelcomics.data.models.dto

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)