package com.klaudiak.marvelcomics.domain

import com.klaudiak.marvelcomics.data.models.local.ComicItem
import com.klaudiak.marvelcomics.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ComicRepositoryInterface {

    fun getComicList(): Flow<Resource<List<ComicItem>>>
    fun searchComicList(query: String): Flow<Resource<List<ComicItem>>>
     fun getComicInfo(id: String) : Flow<Resource<ComicItem>>
}