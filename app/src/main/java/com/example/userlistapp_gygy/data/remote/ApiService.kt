package com.example.userlistapp_gygy.data.remote

import com.example.userlistapp_gygy.data.model.User // Kendi yazdığımız User sınıfını bağlıyoruz
import retrofit2.http.GET

interface ApiService {
    // "users" adresine bir GET isteği at ve bize User listesi getir diyoruz
    @GET("users")
    suspend fun getUsers(): List<User>
}