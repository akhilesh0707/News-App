package com.aqube.newsapp.ui.newslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aqube.newsapp.R
import com.aqube.newsapp.model.News
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * NewViewHolder to bind news into view
 * @param view: selected view
 */
class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    /**
     * Bind news detail to view and loading image to ImageView
     * @param news : news object
     * @param requestManager : used to load image on ImageView
     * @param clickListener : Item click listener object
     */
    fun bind(news: News?, requestManager: RequestManager, clickListener: (News) -> Unit) {
        if (news != null) {
            itemView.txt_news_name.text = news.title
            requestManager.load(news.urlToImage).into(itemView.img_news_banner)
            itemView.text_view_source.text =
                itemView.resources.getString(R.string.label_source) + news.source?.name
            itemView.setOnClickListener { clickListener(news) }
        }
    }

    /**
     * Inflating view xml to NewsViewHolder and return NewsViewHolder
     * @return : NewsViewHolder
     */
    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
            return NewsViewHolder(view)
        }
    }
}