package com.example.phonebook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.phonebook.R
import com.example.phonebook.databinding.FragmentNumberDetailsBinding
import com.example.phonebook.viewModel.MainViewModel

class NumberDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentNumberDetailsBinding
    private val binding get() = _binding
    private val viewModel: MainViewModel by activityViewModels()
    private val navArgs: NumberDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberDetailsBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViews(navArgs.phoneNumber)
    }

    private fun setViews(phoneNumber: String) {
        val detail = viewModel.getContactDetail(phoneNumber)

        binding.tvLastName.text = String.format("${detail.firstName}  ${detail.lastName}")
        binding.emailAddress.text = detail.email
        binding.otherNumber.text = detail.otherNumber
        binding.phoneNumber.text = detail.phoneNumber

    }


}