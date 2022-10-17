package com.ugurinci.rickandmorty

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("fragment", "-> " + javaClass.simpleName)
    }
}