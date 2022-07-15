package com.klaudiak.marvelcomics.data.models.dto

data class Result(
    val creators: Creators,
    val description: String?,
    val id: Int,
    val images: List<Image>,
    val thumbnail: Thumbnail,
    val title: String,
    val urls: List<Url>,
)