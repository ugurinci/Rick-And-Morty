package com.ugurinci.rickandmorty.feature.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentLocationListBinding

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

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayOf("Earth (C-137)", "Abadango"))

        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            findNavController().navigate(LocationListFragmentDirections.actionLocationListFragmentToLocationDetailFragment(position))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}