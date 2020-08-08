package com.example.app_ointmentt.databasing

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIObject {
    private const val BASE_URL = "https://iut-appointment-backend.herokuapp.com"

    private fun retrofitService(): Retrofit
    {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val api: APICalls by lazy{
        retrofitService().create(APICalls::class.java)
    }
}