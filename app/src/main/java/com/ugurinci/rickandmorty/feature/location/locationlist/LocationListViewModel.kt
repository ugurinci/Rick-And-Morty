package com.ugurinci.rickandmorty.feature.location.locationlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.location.LocationListModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationListViewModel : ViewModel() {

    private var _locationList = MutableStateFlow<LocationListModel?>(null)
    val locationList = _locationList.asStateFlow()

    init {
        viewModelScope.launch {
            _locationList.value = RickAndMortyApi.rickAndMortyService.getLocationList()
        }
    }
}