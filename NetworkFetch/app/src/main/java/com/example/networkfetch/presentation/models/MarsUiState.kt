package com.example.networkfetch.presentation.models

import com.example.networkfetch.network.Photos

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object  Loading: MarsUiState
}