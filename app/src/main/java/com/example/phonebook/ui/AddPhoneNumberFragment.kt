package com.example.phonebook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.phonebook.data.ContactDetail
import com.example.phonebook.databinding.FragmentAddPhoneNumberBinding
import com.example.phonebook.utils.ValidateFormInput
import com.example.phonebook.viewModel.MainViewModel

class AddPhoneNumberFragment : Fragment() {

    private lateinit var _binding: FragmentAddPhoneNumberBinding
    private val binding: FragmentAddPhoneNumberBinding get() = _binding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPhoneNumberBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickEvent()

    }


    private fun clickEvent() {

        binding.btnSave.setOnClickListener {
            val phoneNumber = binding.tvPhoneNumber.text.toString()
            val otherNumber = binding.tvOtherNumber.text.toString()
            val firstName = binding.tvFirstName.text.toString()
            val lastName = binding.tvLastName.text.toString()
            val email = binding.tvEmail.text.toString()

            val contactDetail = ContactDetail(
                phoneNumber = phoneNumber, firstName = firstName,
                lastName = lastName, email = email, otherNumber = otherNumber
            )

            if (allInputsAreValidated(contactDetail)){
                viewModel.saveContact(contactDetail)
                Toast.makeText(context, "Contact Saved", Toast.LENGTH_SHORT).show()

                onDetach()
                val action = AddPhoneNumberFragmentDirections
                    .actionAddPhoneNumberFragmentToNumberListFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun allInputsAreValidated(contactDetail: ContactDetail): Boolean{

        if (viewModel.isNumberAlreadySaved(contactDetail.phoneNumber)) {
            Toast.makeText(activity, "Number already exists", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!ValidateFormInput().validateFirstName(contactDetail.firstName)) {
            Toast.makeText(activity, "Invalid First Name", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!ValidateFormInput().validatePhoneNumberInput(contactDetail.phoneNumber)) {
            Toast.makeText(activity, "Invalid phone number", Toast.LENGTH_SHORT).show()
            return false
        }

        if (contactDetail.phoneNumber.isEmpty()){
            Toast.makeText(activity, "Phone number cannot be blank", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!ValidateFormInput().validatePhoneNumberInput(contactDetail.otherNumber ?: "")) {
            Toast.makeText(activity, "Other number is invalid", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!(ValidateFormInput().validateEmail(contactDetail.email))) {
            Toast.makeText(activity, "Invalid Email", Toast.LENGTH_SHORT).show()
            return false
        }

        return true

    }


}