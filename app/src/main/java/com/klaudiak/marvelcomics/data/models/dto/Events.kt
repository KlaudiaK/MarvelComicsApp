package com.klaudiak.marvelcomics.data.models.dto

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)