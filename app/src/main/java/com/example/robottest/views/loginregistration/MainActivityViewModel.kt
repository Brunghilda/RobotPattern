package com.example.robottest.views.loginregistration

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class MainActivityViewModel : ViewModel() {
    val loginInProgress = mutableStateOf(false)

    /**
     * Just a stupid login function that waits 5 seconds and returns true when email will be
     * "abc@mail.de" and password will be "123456" otherwise false will be returned.
     */
    suspend fun login(email: String, password: String): Boolean {
        loginInProgress.value = true
        delay(5000)
        loginInProgress.value = false
        return email == "abc@mail.de" && password == "123456"
    }
}