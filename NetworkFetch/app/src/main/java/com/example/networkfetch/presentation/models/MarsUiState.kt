package com.example.networkfetch.presentation.models

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object  Loading: MarsUiState
}