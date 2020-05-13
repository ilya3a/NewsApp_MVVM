package com.yoyo.newsapp_mvvm.repositories

import android.view.View
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.yoyo.newsapp_mvvm.api.RetrofitBuilder
import com.yoyo.newsapp_mvvm.data_models.NewsModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object NewsRepository {
    private var job: CompletableJob? = null
    private var lastGoodResult: LiveData<NewsModel>? = null

    /*    fun getNews(requestedNews:String,apiKey :String):LiveData<NewsModel2>{
           job = Job()
           return object : LiveData<NewsModel2>(){
               override fun onActive() {
                   super.onActive()
                   job?.let { theJob->
                       CoroutineScope(IO +theJob).launch {
   //                        val str = RetrofitBuilder.apiService.getNews(requestedNews,apiKey)
                           val news : NewsModel2 = RetrofitBuilder.apiService.getNews(requestedNews,apiKey)
   //                        value= news //cannot update liveData on background Tread should use postValue(news)
   //                        postValue(news) //or run it this way
                           withContext(Main){
                               value = news
                               theJob.complete()
                           }
                       }

                   }
               }
           }

       }*/
    fun cancelJobs() {
        job?.cancel()
    }

    fun getNews(requestedNews: String, apiKey: String, view: View): LiveData<NewsModel> {
        job = Job()
        return object : LiveData<NewsModel>() {
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO + it).launch {
                        try {
                            val news = RetrofitBuilder.apiService.getNews(requestedNews, apiKey)
                            withContext(Main) {
                                value = news
                                lastGoodResult?.value = news
                                it.complete()
                            }
                        } catch (e: Exception) {
                            withContext(Main) {
                                cancelJobs()
                                Snackbar.make(view, "No Internet Connection", Snackbar.LENGTH_LONG).show()
                            }
                        }
                    }
                }

            }
        }
    }
}