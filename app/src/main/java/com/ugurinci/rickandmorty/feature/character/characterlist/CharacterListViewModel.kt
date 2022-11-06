package com.ugurinci.rickandmorty.feature.character.characterlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.character.CharacterListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListViewModel : ViewModel() {

    private var _characterList = MutableLiveData<CharacterListModel>()
    val characterList: LiveData<CharacterListModel> = _characterList

    init {
        getCharacterList()
    }

    private fun getCharacterList() {
        RickAndMortyApi.rickAndMortyService.getCharacterList().enqueue(object : Callback<CharacterListModel> {
            override fun onResponse(
                call: Call<CharacterListModel>,
                response: Response<CharacterListModel>
            ) {
                _characterList.value = response.body()
                Log.i("onResponse", "-> " + "onResponse")
            }

            override fun onFailure(call: Call<CharacterListModel>, t: Throwable) {
                Log.i("onFailure", "-> " + "onFailure")
            }
        })
    }
}