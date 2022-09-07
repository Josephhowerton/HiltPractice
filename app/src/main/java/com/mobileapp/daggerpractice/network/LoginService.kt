package com.mobileapp.daggerpractice.network

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/appUser/login")
    suspend fun authenticateCredentials(@Body requestBody: RequestBody) : Response<UserResponse>
}