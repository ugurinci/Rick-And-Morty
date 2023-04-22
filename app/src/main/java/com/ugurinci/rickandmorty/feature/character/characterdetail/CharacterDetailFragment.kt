package com.ugurinci.rickandmorty.feature.character.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.ugurinci.rickandmorty.feature.episode.episodelist.EpisodeListAdapter
import com.ugurinci.rickandmorty.util.StringUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val args: CharacterDetailFragmentArgs by navArgs()

    private val viewModel: CharacterDetailViewModel by viewModels()

    private val episodeListAdapter = EpisodeListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.characterDetail.filterNotNull().collect {
                binding.apply {
                    Glide.with(root).load(it.image).into(imageView)
                    textViewName.text = it.name
                    textViewStatus.text = "Status : " + it.status
                    textViewSpecies.text = "Species : " + it.species
                    textViewType.text = "Type : " + it.type
                    textViewGender.text = "Gender : " + it.gender
                    textViewOrigin.text = "Origin : " + it.origin.name
                    textViewLocation.text = "Location : " + it.location.name
                    textViewCreated.text = "Created : " + it.created
                }
                viewModel.getEpisodes(StringUtil.getLastWordList(it.episode))
            }
        }

        lifecycleScope.launch {
            viewModel.episodeList.filterNotNull().collect {
                episodeListAdapter.episodeList = it
                binding.recyclerView.adapter = episodeListAdapter
            }
        }

        binding.textViewOrigin.setOnClickListener {
            viewModel.characterDetail.value?.origin?.url?.let {
                findNavController().navigate(
                    CharacterDetailFragmentDirections.actionCharacterDetailFragmentToLocationDetailFragment(
                        StringUtil.getLastWordInt(it)
                    )
                )
            }
        }

        binding.textViewLocation.setOnClickListener {
            viewModel.characterDetail.value?.location?.url?.let {
                findNavController().navigate(
                    CharacterDetailFragmentDirections.actionCharacterDetailFragmentToLocationDetailFragment(
                        StringUtil.getLastWordInt(it)
                    )
                )
            }
        }

        episodeListAdapter.click = {
            findNavController().navigate(
                CharacterDetailFragmentDirections.actionCharacterDetailFragmentToEpisodeDetailFragment(
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