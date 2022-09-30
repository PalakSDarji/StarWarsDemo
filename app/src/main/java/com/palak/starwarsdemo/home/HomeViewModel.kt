package com.palak.starwarsdemo.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.palak.starwarsdemo.repo.CharacterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * This viewModel takes care of loading character list in ViewModelScope. Means, it will live with this
 * scope.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(characterRepo: CharacterRepo) : ViewModel() {

    val characterList = characterRepo.getCharsList().cachedIn(viewModelScope)
}