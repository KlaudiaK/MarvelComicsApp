package com.klaudiak.marvelcomics.presentation.search_screen

sealed class SearchScreenEvent {
    class OnQueryChanged(val query: String) : SearchScreenEvent()
}