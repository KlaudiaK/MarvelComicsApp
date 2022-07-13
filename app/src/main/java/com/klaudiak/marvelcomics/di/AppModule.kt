package com.klaudiak.marvelcomics.di

import com.klaudiak.marvelcomics.data.ComicApi
import com.klaudiak.marvelcomics.domain.ComicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ComicApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    })
                    .build()
            )
            .build()

    @Provides
    @Singleton
    fun provideBooksApi(retrofit: Retrofit): ComicApi =
        retrofit.create(ComicApi::class.java)

    @Singleton
    @Provides
    fun providePokemonRepository(
        api: ComicApi
    ) = ComicRepository(api)
}