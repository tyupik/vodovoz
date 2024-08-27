package com.example.vodovozapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://szorin.vodovoz.ru/"

private var retrofit: Retrofit? = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object TovaryApi {
    val retrofitService: TovarService by lazy {
        retrofit!!.create(TovarService::class.java)
    }
}