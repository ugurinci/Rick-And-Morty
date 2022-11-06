package com.ugurinci.rickandmorty.feature.character.characterdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.character.CharacterResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var _characterDetail = MutableLiveData<CharacterResult>()
    val characterDetail: LiveData<CharacterResult> = _characterDetail

    init {
        getCharacterDetail()
    }

    private fun getCharacterDetail() {
        val id = CharacterDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id.toString()
        RickAndMortyApi.rickAndMortyService.getCharacterById(id).enqueue(object : Callback<CharacterResult> {
            override fun onResponse(
                call: Call<CharacterResult>,
                response: Response<CharacterResult>
            ) {
                _characterDetail.value = response.body()
                Log.i("onResponse", "-> " + "onResponse")
            }

            override fun onFailure(call: Call<CharacterResult>, t: Throwable) {
                Log.i("onFailure", "-> " + "onFailure")
            }
        })
    }
}