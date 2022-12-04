package com.example.letsrecyclethissheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.letsrecyclethissheet.databinding.FragmentContactsBinding
import com.example.letsrecyclethissheet.databinding.FragmentMenuBinding
import com.example.letsrecyclethissheet.model.ContactModel

class ContactsFragment : Fragment() {
    private lateinit var viewModel: ContactsViewModel
    private lateinit var binding: FragmentContactsBinding
    private lateinit var dbHelper: DataBaseHelper
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, container, false)
        viewModel = ViewModelProvider(this)[ContactsViewModel::class.java]
        dbHelper = DataBaseHelper(requireContext())
        contactAdapter = ContactAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            addContactFab.setOnClickListener {
                val action = ContactsFragmentDirections.actionContactsFragmentToAddContactFragment()
                findNavController().navigate(action)
            }
        }

        contactAdapter.setOnClickListener(object : OnContactItemClickListener {
            override fun onContactClick(itemView: TextView, position: Int) {
                Toast.makeText(requireContext(), "${itemView.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onEditClick(contact: ContactModel, position: Int) {
                Toast.makeText(requireContext(), "Edit", Toast.LENGTH_SHORT).show()
            }

            override fun onDeleteClick(contact: ContactModel, position: Int) {
                dbHelper.deleteContact(contact)
                contactAdapter.updateContacts(dbHelper.getContacts())
            }

        })
        binding.contactsRv.apply {
            this.adapter = contactAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        contactAdapter.updateContacts(dbHelper.getContacts())
        super.onResume()
    }
}