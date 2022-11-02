package com.ugurinci.rickandmorty.feature.episode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ugurinci.rickandmorty.BaseFragment
import com.ugurinci.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeDetailFragment : BaseFragment() {

    private var _binding: FragmentEpisodeDetailBinding? = null
    private val binding get() = _binding!!

    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RickAndMortyApi.rickAndMortyService.getEpisodeById(args.id.toString()).enqueue(object : Callback<EpisodeResult> {
            override fun onResponse(
                call: Call<EpisodeResult>,
                response: Response<EpisodeResult>
            ) {
                binding.textView.text = response.body()?.name
                Log.i("onResponse", "-> " + "onResponse")
            }

            override fun onFailure(call: Call<EpisodeResult>, t: Throwable) {
                Log.i("onFailure", "-> " + "onFailure")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}