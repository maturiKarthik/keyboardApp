package com.example.keyboardShorcuts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keyboardShorcuts.R
import com.example.keyboardShorcuts.adapter.ShortcutKeyChildAdapter
import com.example.keyboardShorcuts.model.Keys
import kotlinx.android.synthetic.main.fragment_collection_object.*

class ViewPagerFragmentContainer(private var data: List<Keys>) : Fragment() {

     override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_collection_object, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        displayShortcuts.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = ShortcutKeyChildAdapter(data)
            addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
        }
    }

}