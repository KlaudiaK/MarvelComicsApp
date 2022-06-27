package com.klaudiak.marvelcomics.data

import com.klaudiak.marvelcomics.BuildConfig
import com.klaudiak.marvelcomics.data.models.dto.ComicsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicApi {

    companion object{
        const val BASE_URL = "https://gateway.marvel.com"
        const val API_KEY = BuildConfig.apiKey
        const val HASH = BuildConfig.hash
    }


    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("ts") timestamp: String = "1",
        @Query("apikey") apiKey: String = API_KEY,
        @Query("hash") hash: String = HASH,
        @Query("limit") limit: String = "25",
        @Query("offset") offset: String = "0",
        @Query("orderBy") orderBy: String = "-onsaleDate"
    ) : ComicsDTO

    @GET("/v1/public/comics")
    suspend fun getComicsByTitle(
        @Query("ts") timestamp: String = "1",
        @Query("apikey") apiKey: String = API_KEY,
        @Query("hash") hash: String = HASH,
        @Query("title") title: String,
    ) : ComicsDTO

    @GET("/v1/public/comics")
    suspend fun getComicInfo(
        @Query("ts") timestamp: String = "1",
        @Query("apikey") apiKey: String = API_KEY,
        @Query("hash") hash: String = HASH,
        @Query("id") id : String
    ) : ComicsDTO

}