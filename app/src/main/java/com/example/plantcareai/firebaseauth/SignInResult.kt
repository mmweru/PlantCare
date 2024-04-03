package com.example.plantcareai.firebaseauth

data class SignInResult(
    val data: com.example.plantcareai.firebaseauth.UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)