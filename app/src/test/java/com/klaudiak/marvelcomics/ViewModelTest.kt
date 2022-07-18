package com.klaudiak.marvelcomics

import androidx.lifecycle.viewModelScope
import com.klaudiak.marvelcomics.data.models.local.ComicItem
import com.klaudiak.marvelcomics.domain.ComicRepository
import com.klaudiak.marvelcomics.presentation.comics_list.ComicListState
import com.klaudiak.marvelcomics.presentation.comics_list.ComicListViewModel
import com.klaudiak.marvelcomics.presentation.search_screen.SearchScreenEvent
import com.klaudiak.marvelcomics.utils.Resource
import io.mockk.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
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


    private val comicItem = ComicItem(
        id = 1,
        title = "",
        author = "",
        description = null,
        thumbnail = null,
        uriDetails = ""
    )

    @Before
    fun before() {
        comicRepository = mockk(relaxed = true)
        viewModel = ComicListViewModel(comicRepository)
    }


    @Test
    fun `Test get comic by id`() {

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
    fun `Test event handling`() {
        val query = "empty"
        viewModel.onEvent(SearchScreenEvent.OnQueryChanged(query))
        assert(viewModel.uiState.value.query == query)

    }


    @Test
    fun `Test check if query changed func not execute`() {
        val comicItem2 = comicItem.copy(id = 2)
        val flow_1 = flowOf(
            Resource.Loading(true),
            Resource.Success(data = listOf(comicItem)),
            Resource.Loading(false)
        )
        val flow_2 = flowOf(
            Resource.Loading(true),
            Resource.Success(data = listOf(comicItem2)),
            Resource.Loading(false)
        )
        coEvery { comicRepository.searchComicList("1") } returns flow_1
        coEvery { comicRepository.searchComicList("2") } returns flow_2

        runBlocking {
            viewModel.onEvent(SearchScreenEvent.OnQueryChanged("1"))
            assert(viewModel.uiState.value.query == "1")
            viewModel.onEvent(SearchScreenEvent.OnQueryChanged("2"))
            assert(viewModel.uiState.value.query == "2")
        }
        assertNotNull(viewModel.uiState.value.searchedItems)
        assert(viewModel.uiState.value.searchedItems != listOf(comicItem))
    }

    @Test
    fun `Test get comic list`() {

        val flow = flowOf(
            Resource.Loading(true),
            Resource.Success(data = listOf(comicItem)),
        )
        coEvery { comicRepository.getComicList() } returns flow
        val getComicRes = comicRepository.getComicList()
        if (getComicRes is Resource.Success<*>) {
            assert(viewModel.uiState.value.items == listOf(comicItem))
            assert(!viewModel.uiState.value.isLoading)
        } else if (getComicRes is Resource.Loading<*>) {
            assert(viewModel.uiState.value.isLoading)
        }
    }


    @Test
    fun `Test search comic list when success`() {

        coEvery { comicRepository.searchComicList(any()) } returns
                flow {
                    Resource.Success(data = listOf(comicItem))
                }

        viewModel.onEvent(SearchScreenEvent.OnQueryChanged("1"))
        assert(viewModel.uiState.value.query == "1")
        assertNotNull(viewModel.uiState.value.searchedItems)
        assert(!viewModel.uiState.value.noResults)
    }
}