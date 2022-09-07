package com.mobileapp.daggerpractice.view.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobileapp.daggerpractice.network.impl.LoginSourceImpl
import com.mobileapp.daggerpractice.util.LoginValidator
import com.mobileapp.daggerpractice.util.impl.RepositoryImpl
import com.mobileapp.daggerpractice.viewmodel.MainViewModel


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
}

@Composable
fun LoginScreen(mainViewModel: MainViewModel) =
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        var emailText by remember {
            mutableStateOf("")
        }

        var isEmailValid by remember {
            mutableStateOf(false)
        }

        var passwordText by remember {
            mutableStateOf("")
        }

        var isPasswordValid by remember {
            mutableStateOf(false)
        }

        TextField(
            value = emailText,
            label = { Text(text = "Email") },
            singleLine = true,
            keyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
            onValueChange = {
                emailText = it
                isEmailValid = LoginValidator.validatePhoneNumber(it)
                Log.println(Log.ASSERT, "", isEmailValid.toString())
            },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 15.dp, end = 15.dp)
        )

        TextField(
            value = passwordText,
            label = { Text(text = "Password") },
            singleLine = true,
            onValueChange = {
                passwordText = it
                isPasswordValid = LoginValidator.validatePassword(it)
                Log.println(Log.ASSERT, "", isPasswordValid.toString())
            },
            keyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
        )

        Button(
            onClick = { mainViewModel.authenticateCredentials(emailText, passwordText) },
            enabled = (isEmailValid && isPasswordValid),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 15.dp)

        ) {
            Text(text = "Login")
        }
    }