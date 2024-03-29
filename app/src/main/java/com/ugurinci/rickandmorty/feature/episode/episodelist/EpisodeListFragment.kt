package com.ugurinci.rickandmorty.feature.episode.episodelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentEpisodeListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeListFragment : BaseFragment() {

    private var _binding: FragmentEpisodeListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EpisodeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val episodeListPagingAdapter = EpisodeListPagingAdapter(EpisodeComparator)

        binding.apply {
            recyclerView.adapter = episodeListPagingAdapter

            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        lifecycleScope.launch {
            viewModel.episodeListFlow.collectLatest {
                episodeListPagingAdapter.submitData(it)
            }
        }

        episodeListPagingAdapter.click = {
            findNavController().navigate(
                EpisodeListFragmentDirections.actionEpisodeListFragmentToEpisodeDetailFragment(
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