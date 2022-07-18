package com.klaudiak.marvelcomics.data

import com.klaudiak.marvelcomics.BuildConfig
import com.klaudiak.marvelcomics.data.ComicApi.Companion.API_KEY
import com.klaudiak.marvelcomics.data.models.dto.ComicsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicApi {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com"
        const val API_KEY = "6e9f93910aec59d17b0f0ad5667807b3"
        const val HASH = "0782f2a06cb81811b9652eb06e6c9ea0"
        const val TIMESTAMP = "1"
        const val LIMIT = "25"
        const val OFFSET = "0"
        const val ORDER_BY = "-onsaleDate"
    }


    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("ts") timestamp: String = TIMESTAMP,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("hash") hash: String = HASH,
        @Query("limit") limit: String = LIMIT,
        @Query("offset") offset: String = OFFSET,
        @Query("orderBy") orderBy: String = ORDER_BY
    ): ComicsDTO

    @GET("/v1/public/comics")
    suspend fun getComicsByTitle(
        @Query("ts") timestamp: String = TIMESTAMP,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("hash") hash: String = HASH,
        @Query("title") title: String,
    ): ComicsDTO

    @GET("/v1/public/comics")
    suspend fun getComicInfo(
        @Query("ts") timestamp: String = TIMESTAMP,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("hash") hash: String = HASH,
        @Query("id") id: String
    ): ComicsDTO


}