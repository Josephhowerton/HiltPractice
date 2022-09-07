package com.mobileapp.daggerpractice.util

object LoginValidator {
    private const val PHONE_RULES = "[0-9]{10,}"

    private const val PASSWORD_RULES = "[a-z][^ ]{8,}"

    fun validatePhoneNumber(number: String) : Boolean = PHONE_RULES.toRegex().matches(number)
    fun validatePassword(password: String) : Boolean = PASSWORD_RULES.toRegex().matches(password)
}