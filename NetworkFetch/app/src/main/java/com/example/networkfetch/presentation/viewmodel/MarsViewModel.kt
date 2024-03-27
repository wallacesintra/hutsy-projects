package com.example.networkfetch.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.networkfetch.NetworkFetchApplication
import com.example.networkfetch.data.MarsPhotoRepository
import com.example.networkfetch.data.NetworkMarsPhotoRepository
import com.example.networkfetch.presentation.models.MarsUiState
import kotlinx.coroutines.launch
import java.io.IOException


class MarsViewModel(
    private val marsPhotoRepository: MarsPhotoRepository
): ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {

                val listResult = marsPhotoRepository.getMarsPhoto()[0]
                MarsUiState.Success(
                    listResult
                )
            } catch (e: IOException) {
                MarsUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NetworkFetchApplication)
                val marsPhotoRepository = application.container.marsPhotoRepository
                MarsViewModel(marsPhotoRepository = marsPhotoRepository)
            }
        }
    }
}