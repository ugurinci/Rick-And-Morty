package com.ugurinci.rickandmorty.feature.character.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.character.CharacterListModel
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {

    private var _characterList = MutableLiveData<CharacterListModel>()
    val characterList: LiveData<CharacterListModel> = _characterList

    init {
        viewModelScope.launch {
            _characterList.value = RickAndMortyApi.rickAndMortyService.getCharacterList()
        }
    }
}