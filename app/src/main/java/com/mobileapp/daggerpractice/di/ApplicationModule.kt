package com.mobileapp.daggerpractice.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobileapp.daggerpractice.network.LoginService
import com.mobileapp.daggerpractice.network.LoginSource
import com.mobileapp.daggerpractice.network.impl.LoginSourceImpl
import com.mobileapp.daggerpractice.util.Constants.BASE_URL
import com.mobileapp.daggerpractice.util.Repository
import com.mobileapp.daggerpractice.util.impl.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun providesBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun providesOkHttp() : OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(BASE_URL:String, okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginService = retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun provideLoginSource(loginSourceImpl: LoginSourceImpl): LoginSource = loginSourceImpl

    @Provides
    @Singleton
    fun provideRepository(repository: RepositoryImpl): Repository = repository
}