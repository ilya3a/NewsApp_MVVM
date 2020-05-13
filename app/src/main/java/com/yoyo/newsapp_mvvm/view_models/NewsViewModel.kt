package com.yoyo.newsapp_mvvm.view_models

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yoyo.newsapp_mvvm.data_models.NewsModel
import com.yoyo.newsapp_mvvm.repositories.NewsRepository

class NewsViewModel : ViewModel() {

    private var mContext:Context? = null
    var mNews: LiveData<NewsModel>? = null
    var _requestedNews: MutableLiveData<String> = MutableLiveData()

    fun getArticles(): LiveData<NewsModel>? {
        if (mNews == null) {
            mNews = MutableLiveData()
            loadNews()
        }

        return mNews
    }

    private fun loadNews() {
        mContext?.let {
            mNews = Transformations
                .switchMap(_requestedNews) { requestedNews -> // listening to requestedNews string and if it changes (by calling setRequestedNews) it will trigger the code inside {} and return the NewsModel2 object
                    NewsRepository.getNews(requestedNews, it)
                }
            cancelJobs()
        }
    }

    fun setRequestedNews(requestedNews: String = "usa") {
        if (_requestedNews.value != requestedNews) {
            _requestedNews.value = requestedNews
        }

    }

    fun setContext(context: Context) {
        mContext = context
    }

    fun cancelJobs() {
        NewsRepository.cancelJobs()
    }

}