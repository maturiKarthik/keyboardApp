package com.example.keyboardShorcuts.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keyboardShorcuts.R
import com.example.keyboardShorcuts.adapter.ToolsAdapter
import com.example.keyboardShorcuts.viewmodel.ListFragmentViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var layoutLinaerManager: LinearLayoutManager
    private lateinit var listFragmentViewModel: ListFragmentViewModel

    private val toolsAdapter = ToolsAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Recyclerview
        recyclerView.apply {
            activity?.let {
                layoutManager = LinearLayoutManager(it.applicationContext)
               // layoutManager = GridLayoutManager(it.applicationContext,2)
                layoutLinaerManager = layoutManager as LinearLayoutManager
                adapter = toolsAdapter
                addItemDecoration(DividerItemDecoration(activity?.applicationContext,LinearLayoutManager.VERTICAL))
            }
        }

        listFragmentViewModel = ViewModelProviders.of(this).get(ListFragmentViewModel::class.java)
        listFragmentViewModel.loadPrograms()
        observe()

        //Recycler_scroll
        recyclerView.setOnScrollChangeListener { v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            val firstPosition = layoutLinaerManager.findFirstCompletelyVisibleItemPosition()
            if (firstPosition == 0) {
                constraintLayout2.visibility = View.VISIBLE

            } else {
                constraintLayout2.visibility = View.GONE
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onQueryTextChange(newText: String): Boolean {
                Log.e("RESULT", ">>>>>>>>>>>> entered $newText")
                if (newText.isEmpty()){
                    listFragmentViewModel.loadPrograms()
                }
                if (!newText.isNullOrBlank() && !newText.isNullOrEmpty()){
                    listFragmentViewModel.searchPrograms(newText)
                }

                return false
            }
        })

    }

    private fun observe() {
        listFragmentViewModel.listOfPrograms.observe(viewLifecycleOwner, Observer {
            toolsAdapter.setOnUpdate(it)
        })
    }

    override fun onResume() {
        super.onResume()
        searchView.setQuery("", false)
        searchView.clearFocus()
        material_card.requestFocus()
    }

}