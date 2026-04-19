package com.example.userlistapp_gygy.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.userlistapp_gygy.viewmodel.UserUiState
import com.example.userlistapp_gygy.viewmodel.UserViewModel
import com.example.userlistapp_gygy.view.components.UserItem
import com.example.userlistapp_gygy.view.components.SearchBar

@Composable
fun UserListScreen(userViewModel: UserViewModel = viewModel()) {
    val uiState by userViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        // Arama çubuğu her zaman en üstte kalır
        SearchBar(onSearch = { query ->
            userViewModel.searchUsers(query)
        })

        // Geri kalan alanı Box kaplar
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is UserUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is UserUiState.Success -> {
                    val users = (uiState as UserUiState.Success).users
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(users) { user ->
                            UserItem(user = user)
                        }
                    }
                }
                is UserUiState.Error -> {
                    // HATA BURADA: Hata durumunda ne görüneceğini netleştiriyoruz
                    val errorMessage = (uiState as UserUiState.Error).message
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Bağlantı Hatası",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.Red
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = errorMessage,
                            color = Color.Gray,
                            modifier = Modifier.padding(horizontal = 32.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { userViewModel.fetchUsers() }) {
                            Text("Tekrar Dene")
                        }
                    }
                }
            }
        }
    }
}