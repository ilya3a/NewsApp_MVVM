package com.yoyo.newsapp_mvvm.data_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticleModel2(
    @Expose
    @SerializedName("source")
    val source: Source? = null,
    @Expose
    @SerializedName("author")
    val author: String? = null,
    @Expose
    @SerializedName("title")
    val title: String? = null,
    @Expose
    @SerializedName("description")
    val description: String? = null,
    @Expose
    @SerializedName("url")
    val url: String? = null,
    @Expose
    @SerializedName("urlToImage")
    val urlToImage: String? = null,
    @Expose
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @Expose
    @SerializedName("content")
    val content: String? = null
) {
    data class Source(
        @Expose
        @SerializedName("id")
        val id: String? = null,
        @Expose
        @SerializedName("name")
        val name: String? = null
    )
}