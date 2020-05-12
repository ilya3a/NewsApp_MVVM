package com.yoyo.newsapp_mvvm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoyo.newsapp_mvvm.R
import com.yoyo.newsapp_mvvm.data_models.ArticleModel2
import kotlinx.android.synthetic.main.layout_card_news_item.view.*


class NewsListAdapter(private var newsList: List<ArticleModel2> = ArrayList()) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    class ViewHolder(private val view: View, private val context: Context) : RecyclerView.ViewHolder(view) {

        fun bind(newsCard: ArticleModel2) {
            view.tvNewsCardTitle.text = newsCard.title
            view.tvNewsCardDescription.text = newsCard.description
            Glide.with(context).load(newsCard.urlToImage).thumbnail(0.1f).into(view.ivNewsCardImage)
        }
    }

    fun setArticles(newsList: List<ArticleModel2>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_card_news_item, parent, false)
        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        newsList?.get(position)?.let { list -> holder.bind(list) }
    }

    override fun getItemCount(): Int = newsList!!.size
}