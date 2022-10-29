package com.ugurinci.rickandmorty.feature.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentLocationListBinding
import com.ugurinci.rickandmorty.network.RickAndMortyService
import com.ugurinci.rickandmorty.network.model.location.LocationListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationListFragment : BaseFragment() {

    private var _binding: FragmentLocationListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RickAndMortyService::class.java)

        service.getLocationList().enqueue(object : Callback<LocationListModel> {
            override fun onResponse(
                call: Call<LocationListModel>,
                response: Response<LocationListModel>
            ) {
                val locationList = response.body()?.results?.map { it.name }
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, locationList?.toTypedArray().orEmpty())
                binding.listView.adapter = adapter
                Log.i("onResponse", "-> " + "onResponse")
            }

            override fun onFailure(call: Call<LocationListModel>, t: Throwable) {
                Log.i("onFailure", "-> " + "onFailure")
            }
        })

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            findNavController().navigate(LocationListFragmentDirections.actionLocationListFragmentToLocationDetailFragment(position + 1))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}