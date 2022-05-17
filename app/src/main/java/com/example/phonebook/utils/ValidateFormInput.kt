package com.example.phonebook.utils

import java.util.regex.Pattern


class ValidateFormInput {
    private val validNumbers = "0123456789"
    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun validatePhoneNumberInput(number: String): Boolean {
        if (number.startsWith("0") && number.length != 11) return false

        if (number.startsWith("234") && number.length != 13) return false

        if ((!(number.startsWith("2347") || number.startsWith("2348")
                    || number.startsWith("2349")) && number.length == 13)
            || (!(
                    number.startsWith("081") || number.startsWith("080")
                            || number.startsWith("091") || number.startsWith("090")
                            || number.startsWith("070")
                            || number.startsWith("071")) && number.length == 11)
        ) return false

        number.forEach {
            if (it !in validNumbers) return false
        }

        return true
    }


    fun validateFirstName(name: String): Boolean {
        if (name.isEmpty()) return false

        if (name.length < 2) return false

        return true
    }

    fun validateEmail(email: String?): Boolean {
        if (email.isNullOrEmpty()) return true
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

}
