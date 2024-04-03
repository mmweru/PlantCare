package com.example.plantcareai.firebaseauth

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = "",
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)

