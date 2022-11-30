package com.ugurinci.rickandmorty.feature.character.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
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

        val characterListAdapter = CharacterListAdapter(CharacterComparator)

        binding.apply {
            recyclerView.adapter = characterListAdapter

            val dividerItemDecoration = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
            recyclerView.addItemDecoration(dividerItemDecoration)
        }

        lifecycleScope.launch {
            viewModel.characterListFlow.collectLatest {
                characterListAdapter.submitData(it)
            }
        }

        characterListAdapter.click = {
            findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(it))
        }

        /*lifecycleScope.launch {
            viewModel.characterList.filterNotNull().collect {
                val characterList = it.results.map { characterResult ->
                    characterResult.name
                }
            }
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}