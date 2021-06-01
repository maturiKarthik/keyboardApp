package com.example.keyboardShorcuts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.keyboardShorcuts.R
import com.example.keyboardShorcuts.adapter.ViewPagerAdapter
import com.example.keyboardShorcuts.databinding.FragmentShortKeyBinding
import com.example.keyboardShorcuts.model.Category
import com.example.keyboardShorcuts.util.ToogleSwitch
import com.example.keyboardShorcuts.viewmodel.ShortKeySFragmentViewModel
import kotlinx.android.synthetic.main.fragment_short_key.*


class ShortKeySFragment : Fragment() {

    private var listOfKeyByCategory = mutableListOf<Category>()
    private lateinit var fragmentShortKeyBinding: FragmentShortKeyBinding
    private lateinit var shortKeySFragmentViewModel: ShortKeySFragmentViewModel
    private lateinit var viewpager: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ToogleSwitch.state = false
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentShortKeyBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_short_key,
            container,
            false
        )
        return fragmentShortKeyBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewpager = ViewPagerAdapter(childFragmentManager)

        shortKeySFragmentViewModel =
            ViewModelProviders.of(this).get(ShortKeySFragmentViewModel::class.java)

        val toolClicked = arguments?.get("toolUrl")

        switch1.visibility = View.GONE
        // switch
        switch2.setOnCheckedChangeListener { _, isChecked ->
            ToogleSwitch.state = isChecked
            viewpager.update(listOfKeyByCategory)
        }
        shortKeySFragmentViewModel.onLoad(toolClicked.toString())
        observe()
        backPress?.setOnClickListener {
            activity?.onBackPressed()
        }
    }


    private fun observe() {

        shortKeySFragmentViewModel.errorMsf.observe(viewLifecycleOwner, Observer {
            if (it) Toast.makeText(
                activity?.applicationContext,
                "Error While Loading .. !",
                Toast.LENGTH_SHORT
            ).show()
            progressBar1.visibility = View.GONE
        })


        shortKeySFragmentViewModel.shortcutData.observe(viewLifecycleOwner, Observer { it ->
            title.text = it.title
            description.text = it.meta_description
            progressBar1.visibility = View.INVISIBLE
            if (it.macOnly) switch1.visibility = View.GONE else switch1.visibility =
                View.VISIBLE // Hiding switch on macOnly
            it?.sections?.let { category ->
                category.forEach {
                    // viewpager
                    listOfKeyByCategory.add(it)
                    viewPager.adapter = viewpager
                    viewpager.update(listOfKeyByCategory)
                    tabLayout.setupWithViewPager(viewPager)
                }
            }
        })
    }
}