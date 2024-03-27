package com.example.networkfetch.presentation.models

import com.example.networkfetch.network.MarsPhoto


sealed interface MarsUiState {
    data class Success(val photos: MarsPhoto) : MarsUiState
    object Error : MarsUiState
    object  Loading: MarsUiState
}