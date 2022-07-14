package com.klaudiak.marvelcomics

import com.klaudiak.marvelcomics.data.models.local.ComicItem
import com.klaudiak.marvelcomics.domain.ComicRepository
import com.klaudiak.marvelcomics.presentation.comics_list.ComicListViewModel
import com.klaudiak.marvelcomics.presentation.search_screen.SearchScreenEvent
import com.klaudiak.marvelcomics.utils.Resource
import io.mockk.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


class ComicViewModelTest : TestConfig() {


    lateinit var comicRepository: ComicRepository
    lateinit var viewModel: ComicListViewModel


    @Before
    fun before() {

        comicRepository = mockk()
        viewModel = ComicListViewModel(comicRepository)
    }


    @Test
    fun testGetComicById() {
        val comicItem = ComicItem(
            id = 1,
            title = "",
            author = "",
            description = null,
            thumbnail = null,
            uriDetails = ""
        )
        val comic = flowOf(
            Resource.Success(
                comicItem
            )
        )
        coEvery { comicRepository.getComicInfo(any()) } returns comic
        viewModel.getComicById("1")
        if (comicRepository.getComicInfo("1") is Resource.Success<*>)
            assert(viewModel.uiState.value.comic == comicItem)
    }

    @Test
    fun testOnQueryChanged() {
        val query = "empty"
        viewModel.onEvent(SearchScreenEvent.OnQueryChanged(query))
        assert(viewModel.uiState.value.query == query)

    }
}