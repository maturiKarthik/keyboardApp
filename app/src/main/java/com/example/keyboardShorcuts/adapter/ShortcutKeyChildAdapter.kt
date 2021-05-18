package com.example.keyboardShorcuts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.keyboardShorcuts.R
import com.example.keyboardShorcuts.databinding.ItemShortcutSubchildBinding
import com.example.keyboardShorcuts.model.Keys

//This is used inside ShortcutKeyAdapter.kt
class ShortcutKeyChildAdapter(var dataSet: List<Keys>) :
    RecyclerView.Adapter<ShortcutKeyChildAdapter.ViewHolder>() {

    //  private var shortcutKeyAdapter = ShortcutKeyAdapter(listOf())

    fun onUpdateData(data: List<Keys>) {
        this.dataSet = data
        notifyDataSetChanged()
    }

    class ViewHolder(var view: ItemShortcutSubchildBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val itemShortcutSubchildBinding = DataBindingUtil.inflate<ItemShortcutSubchildBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_shortcut_subchild,
            viewGroup,
            false
        )
        return ViewHolder(itemShortcutSubchildBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.view.key = dataSet[position]
    }

    override fun getItemCount() = dataSet.size

}