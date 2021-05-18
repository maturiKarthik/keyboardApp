package com.example.keyboardShorcuts.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.keyboardShorcuts.fragments.ViewPagerFragmentContainer
import com.example.keyboardShorcuts.model.Category

class ViewPagerAdapter(fragmentManager: FragmentManager, private val listOfKeysByCategory: List<Category>) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        val fragment = ViewPagerFragmentContainer(listOfKeysByCategory[position].shortcuts)
        return fragment
    }

    override fun getCount(): Int = listOfKeysByCategory.size

    override fun getPageTitle(position: Int): CharSequence? {
        return listOfKeysByCategory[position].name
    }
}

