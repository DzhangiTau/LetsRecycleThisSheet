package com.example.letsrecyclethissheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MenuItemAdapter(private val items: List<Pair<String, String>>) :
    RecyclerView.Adapter<MenuItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.menu_item, parent, false)
        return MenuItemViewHolder(context, itemView)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}