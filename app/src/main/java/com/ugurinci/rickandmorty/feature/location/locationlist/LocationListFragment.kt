package com.ugurinci.rickandmorty.feature.location.locationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentLocationListBinding

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

        viewModel.locationList.observe(viewLifecycleOwner) {
            val locationList = it.results.map { locationResult ->
                locationResult.name
            }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, locationList)
            binding.listView.adapter = adapter
        }

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            findNavController().navigate(LocationListFragmentDirections.actionLocationListFragmentToLocationDetailFragment(position + 1))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}