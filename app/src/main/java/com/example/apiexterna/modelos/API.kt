package com.example.apiexterna.modelos

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    private const val BASE_URL="http://192.168.1.131:8080/quizbet/"

    val retrofitService: ApiService by lazy{
        getRetrofit().create(ApiService::class.java)
    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}