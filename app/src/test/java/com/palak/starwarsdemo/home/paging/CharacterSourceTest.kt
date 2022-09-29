package com.palak.starwarsdemo.home.paging

import androidx.paging.PagingSource
import com.palak.starwarsdemo.api.ApiInterface
import com.palak.starwarsdemo.models.Character
import com.palak.starwarsdemo.models.CharacterListResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

@RunWith(MockitoJUnitRunner::class)
@Config(sdk = [32])
class CharacterSourceTest {

    private val mockChars =
        listOf(Character(name = "Testing1"), Character(name = "Testing2"))

    @Mock
    private lateinit var apiInterface: ApiInterface

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun shouldLoadDataWhenPassedCorrectly() = runTest {

        //Passing next url is necessary otherwise, code breaks testcase. Pass it as dummy value.
        `when`(apiInterface.getCharacters(1)).thenReturn(
            CharacterListResponse(
                results = mockChars,
                count = 1,
                next = "https://swapi.dev/api/people/?page=2",
                previous = ""
            )
        )
        val source = CharacterSource(apiInterface)

        assertEquals(
            PagingSource.LoadResult.Page(
                data = listOf(mockChars[0], mockChars[1]),
                prevKey = null,
                nextKey = 1
            ),
            source.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = true
                )
            )
        )
    }
}