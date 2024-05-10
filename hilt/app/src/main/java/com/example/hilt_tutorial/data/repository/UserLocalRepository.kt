package com.example.hilt_tutorial.data.repository

import com.example.hilt_tutorial.data.local.UserDao
import javax.inject.Inject

class UserLocalRepository @Inject constructor(
    private val userDao: UserDao
){
    fun getAllUsers() = userDao.getAllUsers()

    fun getSingleUserById(userId: Int) = userDao.getUserById(userId)
}