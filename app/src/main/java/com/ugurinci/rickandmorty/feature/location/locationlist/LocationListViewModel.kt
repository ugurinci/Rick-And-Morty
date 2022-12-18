package com.ugurinci.rickandmorty.feature.location.locationlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ugurinci.rickandmorty.network.RickAndMortyService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationListViewModel @Inject constructor(private val rickAndMortyService: RickAndMortyService) : ViewModel() {

    val locationListFlow = Pager(PagingConfig(20)) {
        LocationListPagingSource(rickAndMortyService)
    }.flow.cachedIn(viewModelScope)
}