package com.example.letsrecyclethissheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.letsrecyclethissheet.databinding.FragmentAddContactBinding
import com.example.letsrecyclethissheet.model.ContactModel

class AddContactFragment : Fragment() {
    private lateinit var viewModel: AddContactViewModel
    private lateinit var binding: FragmentAddContactBinding
    private lateinit var dbHelper: DataBaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_contact, container, false)
        viewModel = ViewModelProvider(this)[AddContactViewModel::class.java]
        dbHelper = DataBaseHelper(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            submitContactBtn.setOnClickListener {
                addToDB(
                    binding.enterNameEt.text.toString(),
                    binding.enterPhoneEt.text.toString()
                )
            }
        }
    }

    private fun addToDB(name: String, phone: String) {
        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(requireContext(), "Name or Phone cannot be empty", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val status = dbHelper.addContact(ContactModel(name = name, phone = phone))

        if (status > -1) {
            Toast.makeText(requireContext(), "Contact was saved", Toast.LENGTH_SHORT).show()
            binding.enterNameEt.text?.clear()
            binding.enterPhoneEt.text?.clear()

            findNavController().popBackStack()
        }
    }
}