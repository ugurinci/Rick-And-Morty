package com.ugurinci.rickandmorty.feature.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentCharacterListBinding
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.character.CharacterListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListFragment : BaseFragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RickAndMortyApi.rickAndMortyService.getCharacterList().enqueue(object : Callback<CharacterListModel> {
            override fun onResponse(
                call: Call<CharacterListModel>,
                response: Response<CharacterListModel>
            ) {
                val characterList = response.body()?.results?.map { it.name }
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, characterList?.toTypedArray().orEmpty())
                binding.listView.adapter = adapter
                Log.i("onResponse", "-> " + "onResponse")
            }

            override fun onFailure(call: Call<CharacterListModel>, t: Throwable) {
                Log.i("onFailure", "-> " + "onFailure")
            }
        })

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(position + 1))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}