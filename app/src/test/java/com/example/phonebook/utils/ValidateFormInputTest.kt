package com.example.phonebook.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ValidateFormInputTest{
    lateinit var validate: ValidateFormInput

    @Before
    fun setUp(){
        validate = ValidateFormInput()
    }

    @Test
    fun `validate phone number starts with 0 and length not equal to 11 return false`(){
        val result = validate.validatePhoneNumberInput("090999999990")
        assertThat(result).isFalse()
    }

    @Test
    fun `validate phone number starts with 0 and length equal to 11 return true`(){
        val result = validate.validatePhoneNumberInput("08131200463")
        assertThat(result).isTrue()
    }

    @Test
    fun `validate phone number starts with 234 and length not equal to 13 return false`(){
        val result = validate.validatePhoneNumberInput("23490909999999")
        assertThat(result).isFalse()
    }

    @Test
    fun `validate phone number starts with 2347 return true`(){
        val result = validate.validatePhoneNumberInput("2347131200463")
        assertThat(result).isTrue()
    }

    @Test
    fun `validate phone number has a non numeric input return false`(){
        val result = validate.validatePhoneNumberInput("070909l9999")
        assertThat(result).isFalse()
    }

    @Test
    fun `validate name input is empty return false`(){
        val result = validate.validateFirstName("")
        assertThat(result).isFalse()
    }

    @Test
    fun `validate empty email address return true`(){
        val result = validate.validateEmail("")
        assertThat(result).isTrue()
    }

    @Test
    fun `validate non empty invalid email address return false`(){
        val result = validate.validateEmail("klj")
        assertThat(result).isFalse()
    }

    @Test
    fun `validate non empty valid email address return true`(){
        val result = validate.validateEmail("abcnigerialtd@yahoo.com")
        assertThat(result).isTrue()
    }

}