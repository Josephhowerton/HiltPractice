package com.mobileapp.daggerpractice.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mobileapp.daggerpractice.util.Result
import com.mobileapp.daggerpractice.view.screens.LoginScreen
import com.mobileapp.daggerpractice.view.theme.DaggerPracticeTheme
import com.mobileapp.daggerpractice.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaggerPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen(viewModel)
                }
            }

            viewModel.loginResponse.collectAsState().value?.let {
                when(it) {
                    is Result.Success -> {
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    }

                    is Result.Error -> {
                        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
                        Log.println(Log.ASSERT, "MainActivity", it.e.toString())
                    }
                }
            }
        }
    }
}