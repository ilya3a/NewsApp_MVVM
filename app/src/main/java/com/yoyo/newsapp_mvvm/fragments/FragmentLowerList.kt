package com.yoyo.newsapp_mvvm.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.yoyo.newsapp_mvvm.R
import com.yoyo.newsapp_mvvm.adapters.NewsListAdapter
import com.yoyo.newsapp_mvvm.data_models.ArticleModel2
import com.yoyo.newsapp_mvvm.view_models.NewsViewModel
import kotlinx.android.synthetic.main.enter_news.view.*
import kotlinx.android.synthetic.main.fragment_lower_list.view.*

class FragmentLowerList() : Fragment() {


    private lateinit var mNewsViewModel: NewsViewModel
    private lateinit var mRecyclerView: RecyclerView
    private var mArticleList: List<ArticleModel2> = ArrayList()
    private lateinit var mRecyclerViewAdapter: NewsListAdapter


    val NEWS_BASE_URL = "https://newsapi.org/v2/everything?q=" //israel;
    val API_KEY = "&apiKey=7c46ec14171146958d70df2056a62308"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_lower_list, container, false)
        var reqNews: String
        mRecyclerView = rootView.recyclerViewOfDays
        initRecyclerView()
        mNewsViewModel = activity?.let { ViewModelProvider(it).get(NewsViewModel::class.java) }!!
        mNewsViewModel.setView(rootView)
        mNewsViewModel._requestedNews.observe(activity!!, Observer { req ->
            reqNews = req
            if (!reqNews.isBlank()) {
                rootView.pickNewsTV.visibility = View.GONE
                mRecyclerView.visibility = View.VISIBLE
                mNewsViewModel.setRequestedNews(reqNews)
            } else {
                rootView.pickNewsTV.visibility = View.VISIBLE
                mRecyclerView.visibility = View.GONE
            }
        })

        mNewsViewModel.getArticles()?.observe(activity!!, Observer { news ->
            mArticleList = news.articles
            if (mArticleList.isEmpty()) {
                Snackbar.make(rootView, "No News Available on this topic", Snackbar.LENGTH_LONG).show()
                rootView.pickNewsTV.visibility = View.VISIBLE
                mRecyclerView.visibility = View.GONE
            }
            mRecyclerViewAdapter = NewsListAdapter(mArticleList)
            mRecyclerView.adapter = mRecyclerViewAdapter
        })
        rootView.testButton.setOnClickListener {
            val v = inflater.inflate(R.layout.enter_news, null)
            AlertDialog.Builder(context)
                .setView(v)
                .setTitle("pick an Interest")
                .setPositiveButton("Done", DialogInterface.OnClickListener { dialog, which ->
                    if (!v.newsET.text.toString().isBlank())
                        mNewsViewModel.setRequestedNews(v.newsET.text.toString())
                })
                .show()
        }
        return rootView
    }

    private fun initRecyclerView() {
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}

