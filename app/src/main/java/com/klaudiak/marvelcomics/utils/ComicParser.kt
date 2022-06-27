package com.klaudiak.marvelcomics.utils

import com.klaudiak.marvelcomics.data.models.local.ComicItem
import com.klaudiak.marvelcomics.data.models.dto.Result

fun Result.toComic(): ComicItem {
    return ComicItem(
        id = id,
        title = title,
        author = if(creators.items.isNotEmpty()) {
            creators.items[0].name
        } else {
               "Unknown"
        },
        description = description ?: "",
        thumbnail = if(images.isNotEmpty()) {
            images[0].path +"."+ images[0].extension
        } else {
            null
        },
        uriDetails = if(urls.isNotEmpty()){
            urls[0].url
        } else{
                "https://www.marvel.com/comics?&options%5Boffset%5D=0&totalcount=12"
            }
    )
}