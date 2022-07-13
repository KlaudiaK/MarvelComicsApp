package com.klaudiak.marvelcomics.presentation.comics_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klaudiak.marvelcomics.domain.ComicRepository
import com.klaudiak.marvelcomics.presentation.search_screen.SearchScreenEvent
import com.klaudiak.marvelcomics.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicListViewModel @Inject constructor(
    private val comicRepository: ComicRepository
) : ViewModel() {

    private val _uiState = mutableStateOf(ComicListState())
    val uiState: State<ComicListState> = _uiState


    init {
        getComicList()
    }

    private fun searchComicList(query: String = uiState.value.query.lowercase()) {
        _uiState.value = _uiState.value.copy(loadError = "")
        viewModelScope.launch {

            comicRepository.searchComicList(query = query).collect { res ->
                when (res) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(searchedItems = res.data)
                        if (res.data?.isEmpty() == true) {
                            _uiState.value = _uiState.value.copy(noResults = true)
                        } else {
                            _uiState.value = _uiState.value.copy(noResults = false)
                        }
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    }
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(loadError = res.message)
                        _uiState.value = _uiState.value.copy(noResults = true)
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    }

                }
            }

        }
    }


    private fun getComicList() {
        viewModelScope.launch {

            comicRepository.getComicList().collect { res ->
                when (res) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(items = res.data)
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    }
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(isLoading = false)
                        _uiState.value = _uiState.value.copy(loadError = res.message)
                    }
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }


    fun getComicById(id: String) {
        viewModelScope.launch {

            comicRepository.getComicInfo(id = id).collect { res ->
                when (res) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(comic = res.data)
                    }
                    else -> {}
                }
            }
        }

    }

    private fun checkIfQueryChanged() {
        viewModelScope.launch {

            val prevQuery = _uiState.value.query
            delay(3000)
            if (prevQuery == _uiState.value.query) {
                searchComicList(query = uiState.value.query)
            }

        }
    }

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.OnQueryChanged -> {
                _uiState.value = _uiState.value.copy(query = event.query)
                checkIfQueryChanged()
            }
        }
    }
}