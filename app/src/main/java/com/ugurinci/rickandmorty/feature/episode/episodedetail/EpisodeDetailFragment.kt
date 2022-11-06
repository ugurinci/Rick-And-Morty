package com.ugurinci.rickandmorty.feature.episode.episodedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentEpisodeDetailBinding

class EpisodeDetailFragment : BaseFragment() {

    private var _binding: FragmentEpisodeDetailBinding? = null
    private val binding get() = _binding!!

    private val args: EpisodeDetailFragmentArgs by navArgs()

    private val viewModel: EpisodeDetailViewModel by viewModels()

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

        viewModel.episodeDetail.observe(viewLifecycleOwner) {
            binding.textView.text = it.name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}