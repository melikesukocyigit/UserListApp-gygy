package com.example.userlistapp_gygy.viewmodel

import com.example.userlistapp_gygy.data.model.User

// sealed interface: Sadece bu dosya içinde tanımlanan durumları alabilen kapalı bir yapıdır.
sealed interface UserUiState {
    object Loading : UserUiState

    data class Success(val users: List<User>) : UserUiState

    data class Error(val message: String) : UserUiState
}