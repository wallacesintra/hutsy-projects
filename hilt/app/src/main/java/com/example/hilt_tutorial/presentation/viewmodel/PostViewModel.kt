package com.example.hilt_tutorial.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt_tutorial.data.local.UserDao
import com.example.hilt_tutorial.data.local.UserDatabase
import com.example.hilt_tutorial.domain.repository.PostsRepository
import com.example.hilt_tutorial.presentation.models.PostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    private val userDatabase: UserDatabase
): ViewModel() {

//    val posts = postsRepository.getPlaceholderPosts()

    var postState: PostState by mutableStateOf(PostState.Loading)
        private set

    init {
        getPosts()
    }

    val userList = userDatabase.userDao.getAllUsers()

    private fun getPosts(){
        viewModelScope.launch{
            try {

                postState = PostState.Loading
                val postData = postsRepository.getPlaceholderPosts()

                postState = try {
                    PostState.Success(
                        postList = postData
                    )
//                    Log.e("postData", "data: ${postData.toString()}")
                } catch (e: Exception){
                    e.message?.let {
                        PostState.Error(
                            message = it
                        )
                    }!!
                }


            }catch (e: Exception){
                postState = e.message?.let {
                    Log.e("view Error", it)
                    PostState.Error(
                        message = it
                    )
                }!!
            }
        }
    }

}