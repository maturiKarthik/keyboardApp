package com.example.keyboardShorcuts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.keyboardShorcuts.R
import com.example.keyboardShorcuts.databinding.ToolsItemAdapterBinding
import com.example.keyboardShorcuts.fragments.ListFragmentDirections
import com.example.keyboardShorcuts.listener.ViewClickListener
import com.example.keyboardShorcuts.model.Programs
import kotlinx.android.synthetic.main.tools_item_adapter.view.*


class ToolsAdapter(var dataSet: List<Programs>) :
    RecyclerView.Adapter<ToolsAdapter.ViewHolder>(), ViewClickListener {

    fun setOnUpdate(data: List<Programs>) {
        dataSet = data
        notifyDataSetChanged()
    }

    class ViewHolder(val view: ToolsItemAdapterBinding) : RecyclerView.ViewHolder(view.root)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val toolsItemAdapterBinding = DataBindingUtil.inflate<ToolsItemAdapterBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.tools_item_adapter,
            viewGroup,
            false
        )
        return ViewHolder(toolsItemAdapterBinding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.view.tool = dataSet[position]
        viewHolder.view.clickListener = this
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    override fun onViewClick(view: View) {
        view.urlPath.text?.let {
            val action = ListFragmentDirections.actionListFragmentToShortKeySFragment()
            action.toolUrl = it.toString()
            Navigation.findNavController(view).navigate(action)
        }
    }
}
