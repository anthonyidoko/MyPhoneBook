package com.example.phonebook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        detail?.let { detail ->
            binding.tvEmail.text = detail.email
            binding.tvOtherNumber.text = detail.otherNumber
            binding.tvPhoneNumber.text = detail.phoneNumber
            binding.tvFullName.text = String.format("${detail.firstName}  ${detail.lastName}")

        } ?: Toast.makeText(activity, "User does not exist", Toast.LENGTH_SHORT).show()

    }


}