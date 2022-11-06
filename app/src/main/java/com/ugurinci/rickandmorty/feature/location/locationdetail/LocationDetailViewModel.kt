package com.ugurinci.rickandmorty.feature.location.locationdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.location.LocationResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationDetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var _locationDetail = MutableLiveData<LocationResult>()
    val locationDetail: LiveData<LocationResult> = _locationDetail

    init {
        getLocationDetail()
    }

    private fun getLocationDetail() {
        val id = LocationDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id.toString()
        RickAndMortyApi.rickAndMortyService.getLocationById(id).enqueue(object : Callback<LocationResult> {
            override fun onResponse(
                call: Call<LocationResult>,
                response: Response<LocationResult>
            ) {
                _locationDetail.value = response.body()
                Log.i("onResponse", "-> " + "onResponse")
            }

            override fun onFailure(call: Call<LocationResult>, t: Throwable) {
                Log.i("onFailure", "-> " + "onFailure")
            }
        })
    }
}