package com.aqube.newsapp.ui.newslist.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.aqube.newsapp.model.News
import com.bumptech.glide.RequestManager

/**
 * News RecyclerView Adapter to show list
 * @param requestManager: RequestManger glide to load images of news
 * @param clickListener : Item click listener
 */
class NewsListAdapter(var requestManager: RequestManager, val clickListener: (News) -> Unit) :
    PagedListAdapter<News, NewsViewHolder>(
        NewsDiffCallback
    ) {

    /**
     * OnCreateViewHolder to create view holder of adapter
     * @param parent : ViewGroup
     * @param viewType : ViewType if used multiple view type
     * @return : return the created ViewHolder (NewsViewHolder)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.create(parent)
    }

    /**
     * OnBindView is to bind the view and set the data to ViewHolder
     * @param holder : NewsViewHolder
     * @param position : ViewHolder position
     */
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position), requestManager, clickListener)
    }

    /***
     * DiffUtil to find difference between old list items and new items
     */
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