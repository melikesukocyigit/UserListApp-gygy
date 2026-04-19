package com.example.userlistapp_gygy.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // by lazy: Bu motor sadece ona ilk ihtiyaç duyduğumuz anda oluşturulur, hafızayı yormaz.
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // JSON metnini bizim yazdığımız User.kt sınıfına otomatik çevirecek eklenti
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}