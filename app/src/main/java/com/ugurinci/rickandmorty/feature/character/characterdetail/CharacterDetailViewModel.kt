package com.ugurinci.rickandmorty.feature.character.characterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyService
import com.ugurinci.rickandmorty.network.model.character.CharacterResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle, private val rickAndMortyService: RickAndMortyService) : ViewModel() {

    private var _characterDetail = MutableStateFlow<CharacterResult?>(null)
    val characterDetail = _characterDetail.asStateFlow()

    init {
        viewModelScope.launch {
            val id = CharacterDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id.toString()
            _characterDetail.value = rickAndMortyService.getCharacterById(id)
        }
    }
}