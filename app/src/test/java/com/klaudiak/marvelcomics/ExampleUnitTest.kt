package com.klaudiak.marvelcomics

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.klaudiak.marvelcomics.data.ComicApi
import com.klaudiak.marvelcomics.data.models.dto.*
import com.klaudiak.marvelcomics.data.models.local.ComicItem
import com.klaudiak.marvelcomics.domain.ComicRepository
import com.klaudiak.marvelcomics.presentation.comics_list.ComicListViewModel
import com.klaudiak.marvelcomics.utils.Resource
import com.klaudiak.marvelcomics.utils.toComic
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.invoke
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


open class TestConfig {

    @ExperimentalCoroutinesApi
    private val dispatcher = StandardTestDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    open fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    open fun clean() {
        Dispatchers.resetMain()
        unmockkAll()
    }
}



