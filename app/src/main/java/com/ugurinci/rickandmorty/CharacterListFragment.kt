package com.ugurinci.rickandmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ugurinci.rickandmorty.databinding.FragmentCharacterListBinding

class CharacterListFragment : BaseFragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnClickListener {
            findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(0))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}