package com.ugurinci.rickandmorty.feature.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentLocationDetailBinding
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.location.LocationResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationDetailFragment : BaseFragment() {

    private var _binding: FragmentLocationDetailBinding? = null
    private val binding get() = _binding!!

    private val args: LocationDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RickAndMortyApi.rickAndMortyService.getLocationById(args.id.toString()).enqueue(object : Callback<LocationResult> {
            override fun onResponse(
                call: Call<LocationResult>,
                response: Response<LocationResult>
            ) {
                binding.textView.text = response.body()?.name
                Log.i("onResponse", "-> " + "onResponse")
            }

            override fun onFailure(call: Call<LocationResult>, t: Throwable) {
                Log.i("onFailure", "-> " + "onFailure")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}