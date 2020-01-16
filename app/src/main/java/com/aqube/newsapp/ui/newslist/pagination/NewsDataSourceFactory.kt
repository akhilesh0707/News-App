package com.aqube.newsapp.ui.newslist.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.aqube.newsapp.model.News
import com.aqube.newsapp.ui.newslist.NewsRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * NewsDataSourceFactory is used to get the data and initializing the NewsDataSource to get the LiveData
 */
class NewsDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val repository: NewsRepository
) : DataSource.Factory<Int, News>() {

    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    /**
     * Used to create the NewsDataSource and return Data
     */
    override fun create(): DataSource<Int, News> {
        val newsDataSource = NewsDataSource(compositeDisposable, repository)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}