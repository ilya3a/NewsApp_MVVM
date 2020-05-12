package com.yoyo.newsapp_mvvm.api

import com.yoyo.newsapp_mvvm.data_models.NewsModel2
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//?q=israel&apiKey=7c46ec14171146958d70df2056a62308

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q")requestedNews: String,
        @Query("apiKey")apiKey: String
    ): NewsModel2
}