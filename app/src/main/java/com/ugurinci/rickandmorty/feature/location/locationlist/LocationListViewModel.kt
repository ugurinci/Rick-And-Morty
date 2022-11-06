package com.ugurinci.rickandmorty.feature.location.locationlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.location.LocationListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationListViewModel : ViewModel() {

    private var _locationList = MutableLiveData<LocationListModel>()
    val locationList: LiveData<LocationListModel> = _locationList

    init {
        getLocationList()
    }

    private fun getLocationList() {
        RickAndMortyApi.rickAndMortyService.getLocationList().enqueue(object : Callback<LocationListModel> {
            override fun onResponse(
                call: Call<LocationListModel>,
                response: Response<LocationListModel>
            ) {
                _locationList.value = response.body()
                Log.i("onResponse", "-> " + "onResponse")
            }

            override fun onFailure(call: Call<LocationListModel>, t: Throwable) {
                Log.i("onFailure", "-> " + "onFailure")
            }
        })
    }
}