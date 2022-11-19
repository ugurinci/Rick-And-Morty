package com.ugurinci.rickandmorty.feature.location.locationlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyService
import com.ugurinci.rickandmorty.network.model.location.LocationListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationListViewModel @Inject constructor(private val rickAndMortyService: RickAndMortyService) : ViewModel() {

    private var _locationList = MutableStateFlow<LocationListModel?>(null)
    val locationList = _locationList.asStateFlow()

    init {
        viewModelScope.launch {
            _locationList.value = rickAndMortyService.getLocationList()
        }
    }
}