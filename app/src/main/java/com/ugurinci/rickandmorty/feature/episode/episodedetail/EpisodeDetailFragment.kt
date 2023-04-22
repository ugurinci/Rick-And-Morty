package com.ugurinci.rickandmorty.feature.episode.episodedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.ugurinci.rickandmorty.feature.character.characterlist.CharacterListAdapter
import com.ugurinci.rickandmorty.util.StringUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeDetailFragment : BaseFragment() {

    private var _binding: FragmentEpisodeDetailBinding? = null
    private val binding get() = _binding!!

    private val args: EpisodeDetailFragmentArgs by navArgs()

    private val viewModel: EpisodeDetailViewModel by viewModels()

    private val characterListAdapter = CharacterListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.episodeDetail.filterNotNull().collect {
                binding.apply {
                    textViewName.text = it.name
                    textViewAirDate.text = "Air Date : " + it.airDate
                    textViewEpisode.text = "Episode : " + it.episode
                    textViewCreated.text = "Created : " + it.created
                }
                viewModel.getCharacters(StringUtil.getLastWordList(it.characters))
            }
        }

        lifecycleScope.launch {
            viewModel.characterList.filterNotNull().collect {
                characterListAdapter.characterList = it
                binding.recyclerView.adapter = characterListAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}