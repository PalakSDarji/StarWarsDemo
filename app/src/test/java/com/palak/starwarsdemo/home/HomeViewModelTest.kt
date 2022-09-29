package com.palak.starwarsdemo.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.palak.starwarsdemo.MainCoroutineRule
import com.palak.starwarsdemo.models.Character
import com.palak.starwarsdemo.repo.CharacterRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [32])
class HomeViewModelTest {

    @Mock
    private lateinit var repo: CharacterRepo

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Spy
    private val charLiveData: MutableLiveData<PagingData<Character>> =
        MutableLiveData(PagingData.from(listOf(Character(name = "Testing"))))

    @Test
    fun shouldLoadListWhenObserved() {

        `when`(repo.getCharsList()).thenReturn(charLiveData)

        val homeViewModel = HomeViewModel(repo)

        val observer = Observer<PagingData<Character>> {}

        try {
            homeViewModel.characterList.observeForever(observer)
            assertNotNull(homeViewModel.characterList.value)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}