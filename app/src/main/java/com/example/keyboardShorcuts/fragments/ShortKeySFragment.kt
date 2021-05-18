package com.example.keyboardShorcuts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.keyboardShorcuts.R
import com.example.keyboardShorcuts.adapter.ViewPagerAdapter
import com.example.keyboardShorcuts.model.Category
import com.example.keyboardShorcuts.viewmodel.ShortKeySFragmentViewModel
import kotlinx.android.synthetic.main.fragment_short_key.*


class ShortKeySFragment : Fragment() {

    private var listOfKeyByCategory = mutableListOf<Category>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_short_key, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shortKeySFragmentViewModel =
            ViewModelProviders.of(this).get(ShortKeySFragmentViewModel::class.java)
        val toolClicked = arguments?.get("toolUrl")
        shortKeySFragmentViewModel.onLoad(toolClicked.toString())

        observe(shortKeySFragmentViewModel)
    }

    private fun observe(shortKeySFragmentViewModel: ShortKeySFragmentViewModel) {

        shortKeySFragmentViewModel.errorMsf.observe(viewLifecycleOwner, Observer {
            if (it) Toast.makeText(
                activity?.applicationContext,
                "Error While Loading .. !",
                Toast.LENGTH_SHORT
            ).show()
        })


        shortKeySFragmentViewModel.shortcutData.observe(viewLifecycleOwner, Observer {
            title.text = it.title
            description.text = it.meta_description.toString()
            val category = it?.sections
            category?.let { category ->
                category.forEach {
                    // viewpager
                    listOfKeyByCategory.add(it)
                    viewPager.adapter = ViewPagerAdapter(childFragmentManager, listOfKeyByCategory)
                    tabLayout.setupWithViewPager(viewPager)
                }
            }
        })
    }
}