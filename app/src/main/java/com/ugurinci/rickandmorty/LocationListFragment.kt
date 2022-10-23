package com.ugurinci.rickandmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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
        binding.root.setOnClickListener {
            findNavController().navigate(LocationListFragmentDirections.actionLocationListFragmentToLocationDetailFragment(0))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}