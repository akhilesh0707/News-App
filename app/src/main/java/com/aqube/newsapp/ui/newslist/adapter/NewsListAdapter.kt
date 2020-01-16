package com.aqube.newsapp.ui.newslist.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.aqube.newsapp.model.News
import com.bumptech.glide.RequestManager

class NewsListAdapter(var requestManager: RequestManager,val clickListener: (News) -> Unit) : PagedListAdapter<News, NewsViewHolder>(
    NewsDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position),requestManager,clickListener)
    }

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }

}