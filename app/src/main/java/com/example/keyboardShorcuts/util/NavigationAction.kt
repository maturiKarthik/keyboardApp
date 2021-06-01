package com.example.keyboardShorcuts.util

import android.view.View
import androidx.navigation.Navigation
import com.example.keyboardShorcuts.fragments.ListFragmentDirections

object NavigationAction {

    fun start(url: String, view: View) {
        val action = ListFragmentDirections.actionListFragmentToShortKeySFragment()
        action.toolUrl = url
        Navigation.findNavController(view).navigate(action)
    }
}