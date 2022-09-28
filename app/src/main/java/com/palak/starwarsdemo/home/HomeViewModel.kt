package com.palak.starwarsdemo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palak.starwarsdemo.models.Character
import com.palak.starwarsdemo.repo.CharacterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val characterRepo: CharacterRepo) : ViewModel() {

    private val charactersMutableLiveData : MutableLiveData<List<Character>> = MutableLiveData()
    val charactersLiveData : LiveData<List<Character>> = charactersMutableLiveData

    fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = characterRepo.getCharactersList()

            if(response.results.isNotEmpty()){
               charactersMutableLiveData.postValue(response.results)
            }
        }
    }
}