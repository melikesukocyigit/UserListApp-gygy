package com.example.userlistapp_gygy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userlistapp_gygy.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.userlistapp_gygy.data.model.User
class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    // Orijinal listeyi yedekte tutmak için
    private var allUsers: List<User> = emptyList<User>()
    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading


            try {
                allUsers = repository.getUsers()
                _uiState.value = UserUiState.Success(allUsers)
            } catch (e: Exception) {
                _uiState.value = UserUiState.Error(e.localizedMessage ?: "Hata")
            }
        }
    }
    // Arama fonksiyonu
    fun searchUsers(query: String) {
        if (query.isEmpty()) {
            _uiState.value = UserUiState.Success(allUsers)
        } else {
            val filteredList = allUsers.filter {
                it.name.contains(query, ignoreCase = true)
            }
            _uiState.value = UserUiState.Success(filteredList)
        }
    }
}