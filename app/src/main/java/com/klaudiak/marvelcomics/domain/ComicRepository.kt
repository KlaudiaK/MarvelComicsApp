package com.klaudiak.marvelcomics.domain

import com.klaudiak.marvelcomics.data.ComicApi
import com.klaudiak.marvelcomics.data.models.local.ComicItem

import com.klaudiak.marvelcomics.data.models.dto.Result
import com.klaudiak.marvelcomics.utils.Resource
import com.klaudiak.marvelcomics.utils.toComic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class ComicRepository @Inject constructor(
    private val api: ComicApi
): ComicRepositoryInterface {

    override fun getComicList(): Flow<Resource<List<ComicItem>>> {
           return flow{
               emit(Resource.Loading(true))
               val booksList = listOf<ComicItem>()
               emit(Resource.Success(data = booksList))
                   val response = try{
                      val comics = mutableListOf<Result>()
                      val responseComics = api.getComics().data.results
                       responseComics.forEach{
                           comics.add(it)
                       }
                      comics
                   } catch (e: Exception) {
                       emit( Resource.Error("An unknown error occurred."))
                       null
                   }

               emit(Resource.Success(response?.map { it.toComic() } ?: listOf()))

               emit(Resource.Loading(false))
               }
    }

    override fun searchComicList(query: String): Flow<Resource<List<ComicItem>>> {
        return flow{
            emit(Resource.Loading(true))
            val booksList = listOf<ComicItem>()
            emit(Resource.Success(data = booksList))

                val response = try{
                    val comics = mutableListOf<Result>()
                    val responseComics =  api.getComicsByTitle(title = query).data.results
                    responseComics.forEach{
                        comics.add(it)
                    }
                    comics
                } catch (e: Exception) {
                    emit(Resource.Error("An unknown error occured."))
                    null
                }
            if (response != null && response.isEmpty()) {
                emit(Resource.Error("No data"))
            }
            else{
                emit(Resource.Success(response?.map { it.toComic() } ?: listOf()))
            }


            emit(Resource.Loading(false))

        }
    }

    override fun getComicInfo(id: String): Flow<Resource<ComicItem>> {

        return flow{
            emit(Resource.Loading(true))

            val response = try {
                api.getComicInfo(id = id).data.results[0]
            } catch( e: Exception) {
                emit( Resource.Error("An unknown error occured."))
                null
            }
            if (response != null) {
                emit(Resource.Success(response.toComic() ))
            }

            emit(Resource.Loading(false))

        }
    }
}