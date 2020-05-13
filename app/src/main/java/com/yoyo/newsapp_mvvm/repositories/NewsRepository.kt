package com.yoyo.newsapp_mvvm.repositories

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.yoyo.newsapp_mvvm.api.RetrofitBuilder
import com.yoyo.newsapp_mvvm.data_models.NewsModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object NewsRepository {
    private val API_KEY = "7c46ec14171146958d70df2056a62308"
    private var job: CompletableJob? = null

    fun cancelJobs() {
        job?.cancel()
    }

    fun getNews(requestedNews: String, context: Context): LiveData<NewsModel> {
        job = Job()
        return object : LiveData<NewsModel>() {
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO + it).launch {
                        try {
                            val news = RetrofitBuilder.apiService.getNews(requestedNews, API_KEY)
                            withContext(Main) {
                                value = news
                                it.complete()
                            }
                        } catch (e: Exception) {
                            withContext(Main) {
                                cancelJobs()
                                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }

            }
        }
    }
}