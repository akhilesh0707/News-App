package com.aqube.newsapp.ui.newslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aqube.newsapp.R
import com.aqube.newsapp.model.News
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.item_news.view.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(news: News?, requestManager: RequestManager, clickListener: (News) -> Unit) {
        if (news != null) {
            itemView.txt_news_name.text = news.title
            requestManager.load(news.urlToImage).into(itemView.img_news_banner)
            itemView.setOnClickListener { clickListener(news) }
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
            return NewsViewHolder(view)
        }
    }
}