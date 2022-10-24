package com.ugurinci.rickandmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ugurinci.rickandmorty.databinding.FragmentLocationDetailBinding

class LocationDetailFragment : BaseFragment() {

    private var _binding: FragmentLocationDetailBinding? = null
    private val binding get() = _binding!!

    private val args: LocationDetailFragmentArgs by navArgs()

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
        binding.textView.text = args.id.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}