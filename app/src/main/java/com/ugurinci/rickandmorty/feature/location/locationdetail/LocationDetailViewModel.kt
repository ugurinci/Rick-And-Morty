package com.ugurinci.rickandmorty.feature.location.locationdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyService
import com.ugurinci.rickandmorty.network.model.character.CharacterResult
import com.ugurinci.rickandmorty.network.model.location.LocationResult
import com.ugurinci.rickandmorty.util.StringUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val rickAndMortyService: RickAndMortyService
) : ViewModel() {

    private var _locationDetail = MutableStateFlow<LocationResult?>(null)
    val locationDetail = _locationDetail.asStateFlow()

    private var _characterList = MutableStateFlow<List<CharacterResult>?>(null)
    val characterList = _characterList.asStateFlow()

    init {
        viewModelScope.launch {
            val id = LocationDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id.toString()
            _locationDetail.value = rickAndMortyService.getLocationById(id)
        }
    }

    fun getCharacters(characterIdList: List<String>) {
        viewModelScope.launch {
            if (characterIdList.size > 1) {
                _characterList.value = rickAndMortyService.getCharacterListByIdList(
                    StringUtil.joinIdListToString(characterIdList)
                )
            } else if (characterIdList.size == 1) {
                _characterList.value = listOf(rickAndMortyService.getCharacterById(characterIdList[0]))
            }
        }
    }
}