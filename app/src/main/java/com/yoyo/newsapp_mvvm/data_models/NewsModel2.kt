package com.yoyo.newsapp_mvvm.data_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsModel2(
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int,
    @SerializedName("articles")
    @Expose
    val articles: List<ArticleModel2>,
    @SerializedName("status")
    @Expose
    val status: String
)
