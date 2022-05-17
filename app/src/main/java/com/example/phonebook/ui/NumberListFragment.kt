package com.example.phonebook.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.phonebook.R
import com.example.phonebook.databinding.FragmentNumberListBinding
import com.example.phonebook.utils.PhoneNumberAdapter
import com.example.phonebook.viewModel.MainViewModel

class NumberListFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var _binding: FragmentNumberListBinding
    private val binding: FragmentNumberListBinding get() = _binding
    private val viewModel: MainViewModel by activityViewModels()
    private val myAdapter by lazy { PhoneNumberAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickEvent()
        setUpAdapter()

    }

    private fun clickEvent() {

        binding.cvAdd.setOnClickListener {
            val action =
                NumberListFragmentDirections.actionNumberListFragmentToAddPhoneNumberFragment()
            findNavController().navigate(action)
        }

        myAdapter.setOnClickListener(object : PhoneNumberAdapter.SetPhoneClickListener {
            override fun onNumberClicked(contact: String) {
                val action =
                    NumberListFragmentDirections.actionNumberListFragmentToNumberDetailsFragment(
                        contact
                    )
                findNavController().navigate(action)
            }
        })

    }

    private fun setUpAdapter() {
        viewModel.phoneNumberLiveData.observe(viewLifecycleOwner, Observer {
            myAdapter.differ.submitList(it.toList())
            binding.contactRecyclerView.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = myAdapter
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        searchContactSetForNumber(query)
        return true
    }


    override fun onQueryTextChange(query: String?): Boolean {
        searchContactSetForNumber(query)
        return true
    }


    private fun searchContactSetForNumber(query: String?) {
        if (query != null) {
            viewModel.searchForPhoneNumberInListOfNamesAndPhoneNumbers(query)
                .observe(viewLifecycleOwner, Observer { contactList ->
                    myAdapter.differ.submitList(contactList)
                })
        }
    }




}