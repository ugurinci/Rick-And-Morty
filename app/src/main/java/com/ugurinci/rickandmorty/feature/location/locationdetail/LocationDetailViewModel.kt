package com.ugurinci.rickandmorty.feature.location.locationdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyService
import com.ugurinci.rickandmorty.network.model.location.LocationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle, private val rickAndMortyService: RickAndMortyService) : ViewModel() {

    private var _locationDetail = MutableStateFlow<LocationResult?>(null)
    val locationDetail = _locationDetail.asStateFlow()

    init {
        viewModelScope.launch {
            val id = LocationDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id.toString()
            _locationDetail.value = rickAndMortyService.getLocationById(id)
        }
    }
}