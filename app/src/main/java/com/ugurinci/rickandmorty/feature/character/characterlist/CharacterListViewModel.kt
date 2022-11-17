package com.ugurinci.rickandmorty.feature.character.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.character.CharacterListModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {

    private var _characterList = MutableStateFlow<CharacterListModel?>(null)
    val characterList = _characterList.asStateFlow()

    init {
        viewModelScope.launch {
            _characterList.value = RickAndMortyApi.rickAndMortyService.getCharacterList()
        }
    }
}