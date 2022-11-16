package com.ugurinci.rickandmorty.feature.location.locationlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.location.LocationListModel
import kotlinx.coroutines.launch

class LocationListViewModel : ViewModel() {

    private var _locationList = MutableLiveData<LocationListModel>()
    val locationList: LiveData<LocationListModel> = _locationList

    init {
        viewModelScope.launch {
            _locationList.value = RickAndMortyApi.rickAndMortyService.getLocationList()
        }
    }
}