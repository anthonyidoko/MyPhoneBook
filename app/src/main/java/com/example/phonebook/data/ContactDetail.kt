package com.example.phonebook.data

data class ContactDetail(
    val phoneNumber: String,
    var otherNumber: String? = null,
    val firstName: String,
    var lastName: String? = null,
    var email: String? = null
)
