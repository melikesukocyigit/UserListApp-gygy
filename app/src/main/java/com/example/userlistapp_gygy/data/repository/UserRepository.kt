package com.example.userlistapp_gygy.data.repository

import com.example.userlistapp_gygy.data.model.User
import com.example.userlistapp_gygy.data.remote.RetrofitInstance

class UserRepository {

    // ViewModel'in çağıracağı, API'den listeyi çeken fonksiyon
    suspend fun getUsers(): List<User> {
        // Gidip Retrofit motorumuzdaki api arayüzünü kullanarak verileri getiriyoruz
        return RetrofitInstance.api.getUsers()
    }
}