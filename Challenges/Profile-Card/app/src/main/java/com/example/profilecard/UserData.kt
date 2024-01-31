package com.example.profilecard

import androidx.annotation.DrawableRes

data class UserData(
    val name: String,
    val email: String,
    val bio: String,
    @DrawableRes val profileImage: Int
)
