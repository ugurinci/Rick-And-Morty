package com.ugurinci.rickandmorty.feature.location.locationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentLocationListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationListFragment : BaseFragment() {

    private var _binding: FragmentLocationListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LocationListViewModel by viewModels()

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

        val locationListPagingAdapter = LocationListPagingAdapter(LocationComparator)

        binding.apply {
            recyclerView.adapter = locationListPagingAdapter

            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        lifecycleScope.launch {
            viewModel.locationListFlow.collectLatest {
                locationListPagingAdapter.submitData(it)
            }
        }

        locationListPagingAdapter.click = {
            findNavController().navigate(
                LocationListFragmentDirections.actionLocationListFragmentToLocationDetailFragment(
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