package com.example.keyboardShorcuts.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.keyboardShorcuts.fragments.ViewPagerFragmentContainer
import com.example.keyboardShorcuts.model.Category

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private var listOfKeysByCategory: List<Category> = listOf()

    fun update(keys: List<Category>) {
        listOfKeysByCategory = keys
        notifyDataSetChanged()
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE //Fragment should be recreated
    }


    override fun getItem(position: Int): Fragment {
        val fragment = ViewPagerFragmentContainer(listOfKeysByCategory[position].shortcuts)
        return fragment
    }

    override fun getCount(): Int = listOfKeysByCategory.size

    override fun getPageTitle(position: Int): CharSequence? {
        return listOfKeysByCategory[position].name
    }
}

