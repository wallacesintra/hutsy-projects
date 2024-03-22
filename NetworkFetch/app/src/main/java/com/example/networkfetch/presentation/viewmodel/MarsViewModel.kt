package com.example.networkfetch.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkfetch.network.MarsApi
import com.example.networkfetch.presentation.models.MarsUiState
import kotlinx.coroutines.launch
import java.io.IOException


class MarsViewModel: ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                val listResult = MarsApi.retrofitService.getPhotos()
                MarsUiState.Success(
                    listResult
                )
            } catch (e: IOException) {
                MarsUiState.Error
            }
        }
    }
}