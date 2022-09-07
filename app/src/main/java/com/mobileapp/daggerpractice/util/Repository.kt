package com.mobileapp.daggerpractice.util

import com.mobileapp.daggerpractice.model.User

interface Repository {
    suspend fun authenticateCredentials(phoneNumber: String, password: String) : Result<User>
}