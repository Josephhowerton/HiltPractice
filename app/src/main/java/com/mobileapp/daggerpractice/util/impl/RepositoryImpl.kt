package com.mobileapp.daggerpractice.util.impl

import com.mobileapp.daggerpractice.model.User
import com.mobileapp.daggerpractice.network.LoginSource
import com.mobileapp.daggerpractice.util.Repository
import com.mobileapp.daggerpractice.util.Result
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val loginSource: LoginSource): Repository {

    override suspend fun authenticateCredentials(phoneNumber: String, password: String): Result<User> =
        loginSource.authenticateCredentials(phoneNumber, password)
}