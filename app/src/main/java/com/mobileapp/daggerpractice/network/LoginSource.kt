package com.mobileapp.daggerpractice.network

import com.mobileapp.daggerpractice.model.User
import com.mobileapp.daggerpractice.util.Result

interface LoginSource {

    suspend fun authenticateCredentials(phoneNumber: String, password: String) : Result<User>
}