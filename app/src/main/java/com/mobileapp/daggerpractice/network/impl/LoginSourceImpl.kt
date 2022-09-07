package com.mobileapp.daggerpractice.network.impl

import com.mobileapp.daggerpractice.model.User
import com.mobileapp.daggerpractice.network.LoginService
import com.mobileapp.daggerpractice.network.LoginSource
import com.mobileapp.daggerpractice.util.Constants.KEY_PASSWORD
import com.mobileapp.daggerpractice.util.Constants.KEY_PHONE_NUMBER
import com.mobileapp.daggerpractice.util.Result
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

class LoginSourceImpl @Inject constructor(private val loginService: LoginService): LoginSource {

    override suspend fun authenticateCredentials(phoneNumber: String, password: String): Result<User> {
        try {
            val jsonObject = JSONObject()
            jsonObject.put(KEY_PHONE_NUMBER, phoneNumber)
            jsonObject.put(KEY_PASSWORD, password)
            val request = jsonObject.toString().toRequestBody(contentType = "application/json".toMediaType())
            val response = loginService.authenticateCredentials(request)

            response.body()?.let {
                val user = User(
                    fullName = it.fullName,
                    mobileNo = it.mobileNo,
                    emailId = it.emailId,
                    userId = it.userId,
                    apiToken = it.apiToken
                )

                return  Result.Success(user)

            } ?: throw Exception("Could not parse response")


        } catch (e: Exception){
            return Result.Error(e)
        }
    }
}