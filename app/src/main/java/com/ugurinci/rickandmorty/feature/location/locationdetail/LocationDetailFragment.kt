package com.ugurinci.rickandmorty.feature.location.locationdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentLocationDetailBinding
import com.ugurinci.rickandmorty.feature.character.characterlist.CharacterListAdapter
import com.ugurinci.rickandmorty.util.StringUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationDetailFragment : BaseFragment() {

    private var _binding: FragmentLocationDetailBinding? = null
    private val binding get() = _binding!!

    private val args: LocationDetailFragmentArgs by navArgs()

    private val viewModel: LocationDetailViewModel by viewModels()

    private val characterListAdapter = CharacterListAdapter()

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

        lifecycleScope.launch {
            viewModel.locationDetail.filterNotNull().collect {
                binding.apply {
                    textViewName.text = it.name
                    textViewType.text = "Type : " + it.type
                    textViewDimension.text = "Dimension : " + it.dimension
                    textViewCreated.text = "Created : " + it.created
                }
                viewModel.getCharacters(StringUtil.getLastWordList(it.residents))
            }
        }

        lifecycleScope.launch {
            viewModel.characterList.filterNotNull().collect {
                characterListAdapter.characterList = it
                binding.recyclerView.adapter = characterListAdapter
            }
        }

        characterListAdapter.click = {
            findNavController().navigate(
                LocationDetailFragmentDirections.actionLocationDetailFragmentToCharacterDetailFragment(
                    it
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}