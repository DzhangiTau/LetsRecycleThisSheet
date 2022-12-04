package com.example.letsrecyclethissheet

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.letsrecyclethissheet.model.ContactModel

class ContactItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val contactItem: FrameLayout = itemView.findViewById(R.id.contact_item_fl)
    private val name: TextView = itemView.findViewById(R.id.contact_name_tv)
    private val phone: TextView = itemView.findViewById(R.id.contact_phone_iv)
    private val editBtn: ImageView = itemView.findViewById(R.id.edit_contact_iv)
    private val deleteBtn: ImageView = itemView.findViewById(R.id.delete_contact_iv)

    fun bind(
        item: ContactModel,
        listener: OnContactItemClickListener,
        position: Int,
    ) {
        phone.text = item.phone
        name.text = item.name

        contactItem.setOnClickListener {
            listener.onContactClick(name, position)
        }

        editBtn.setOnClickListener {
            listener.onEditClick(editBtn, position)
        }

        deleteBtn.setOnClickListener {
            listener.onDeleteClick(deleteBtn, position)
        }
    }
}