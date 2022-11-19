package com.ugurinci.rickandmorty.feature.character.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyService
import com.ugurinci.rickandmorty.network.model.character.CharacterListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val rickAndMortyService: RickAndMortyService) : ViewModel() {

    private var _characterList = MutableStateFlow<CharacterListModel?>(null)
    val characterList = _characterList.asStateFlow()

    init {
        viewModelScope.launch {
            _characterList.value = rickAndMortyService.getCharacterList()
        }
    }
}