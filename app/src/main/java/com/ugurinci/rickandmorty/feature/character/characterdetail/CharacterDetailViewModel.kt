package com.ugurinci.rickandmorty.feature.character.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.character.CharacterResult
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var _characterDetail = MutableLiveData<CharacterResult>()
    val characterDetail: LiveData<CharacterResult> = _characterDetail

    init {
        viewModelScope.launch {
            val id = CharacterDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id.toString()
            _characterDetail.value = RickAndMortyApi.rickAndMortyService.getCharacterById(id)
        }
    }
}