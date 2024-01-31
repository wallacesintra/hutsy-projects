package com.example.profilecard.data

import com.example.profilecard.R
import com.example.profilecard.UserData

object UsersRepository {
    val Users = listOf<UserData>(
        UserData(
            "Jane Joseph",
            "jane.joseph@gamil.com",
            "Freelancers for hire designed by Adrian Goia for Epic Coders",
            R.drawable.femaleprofile
        ),
        UserData(
            "John Doe",
            "john.doe@gamil.com",
            "Freelancers for hire designed by Adrian Goia for Epic Coders",
            R.drawable.maleprofile
        )
    )
}