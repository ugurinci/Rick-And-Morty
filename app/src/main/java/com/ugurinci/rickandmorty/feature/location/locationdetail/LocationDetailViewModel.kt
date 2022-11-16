package com.ugurinci.rickandmorty.feature.location.locationdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.location.LocationResult
import kotlinx.coroutines.launch

class LocationDetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var _locationDetail = MutableLiveData<LocationResult>()
    val locationDetail: LiveData<LocationResult> = _locationDetail

    init {
        viewModelScope.launch {
            val id = LocationDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id.toString()
            _locationDetail.value = RickAndMortyApi.rickAndMortyService.getLocationById(id)
        }
    }
}