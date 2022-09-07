package com.mobileapp.daggerpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileapp.daggerpractice.model.User
import com.mobileapp.daggerpractice.util.Repository
import com.mobileapp.daggerpractice.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val loginResponse = MutableStateFlow<Result<User?>?>(null)

    fun authenticateCredentials(phoneNumber: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginResponse.value = repository.authenticateCredentials(phoneNumber, password)
        }
    }
}