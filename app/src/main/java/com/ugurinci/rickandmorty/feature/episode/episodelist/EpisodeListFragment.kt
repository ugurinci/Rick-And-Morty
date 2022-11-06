package com.ugurinci.rickandmorty.feature.episode.episodelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentEpisodeListBinding

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

        viewModel.episodeList.observe(viewLifecycleOwner) {
            val episodeList = it.results.map { episodeResult ->
                episodeResult.name
            }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, episodeList)
            binding.listView.adapter = adapter
        }

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            findNavController().navigate(EpisodeListFragmentDirections.actionEpisodeListFragmentToEpisodeDetailFragment(position + 1))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}