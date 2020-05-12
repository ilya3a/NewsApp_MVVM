package com.yoyo.newsapp_mvvm.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
   private const val BASE_URL = "https://newsapi.org/" //israel;

    private val retrofitBuilder : Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val apiService : ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }
}