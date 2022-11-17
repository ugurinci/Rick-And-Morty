package com.ugurinci.rickandmorty.feature.character.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentCharacterListBinding
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class CharacterListFragment : BaseFragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterListViewModel by viewModels()

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

        lifecycleScope.launch {
            viewModel.characterList.filterNotNull().collect {
                val characterList = it.results.map { characterResult ->
                    characterResult.name
                }
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, characterList)
                binding.listView.adapter = adapter
            }
        }

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(position + 1))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}