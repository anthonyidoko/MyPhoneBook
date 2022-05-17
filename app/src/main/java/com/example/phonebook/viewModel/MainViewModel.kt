package com.example.phonebook.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.phonebook.data.ContactDetail
import com.example.phonebook.data.NameAndNumber


class MainViewModel : ViewModel() {

    private val setOfPhoneNumbers = mutableSetOf<String>()
    private val listOfNamesAndPhoneNumbers = mutableListOf<NameAndNumber>()
    private val mapOfContactDetails = mutableMapOf<String, ContactDetail>()

    private val _phoneNumbersLiveData = MutableLiveData<MutableList<NameAndNumber>>()
    val phoneNumberLiveData: LiveData<MutableList<NameAndNumber>> = _phoneNumbersLiveData

    fun isNumberAlreadySaved(number: String): Boolean {
        return number in setOfPhoneNumbers
    }

    fun saveContact(contact: ContactDetail) {
        setOfPhoneNumbers.add(contact.phoneNumber)
        listOfNamesAndPhoneNumbers.add(NameAndNumber(contact.firstName, contact.phoneNumber))
        mapOfContactDetails[contact.phoneNumber] = contact
        _phoneNumbersLiveData.value = listOfNamesAndPhoneNumbers
    }

    fun getContactDetail(phoneNumber: String): ContactDetail? {
        return mapOfContactDetails[phoneNumber]

    }

    fun searchForPhoneNumberInListOfNamesAndPhoneNumbers(query: String)
            : LiveData<List<NameAndNumber>> {
        val result =
            listOfNamesAndPhoneNumbers.filter { contact -> contact.number.startsWith(query) }

        return MutableLiveData(result)
    }

}