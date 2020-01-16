package com.aqube.newsapp.ui.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.aqube.newsapp.base.BaseViewModel
import com.aqube.newsapp.base.NetworkState
import com.aqube.newsapp.model.News
import com.aqube.newsapp.ui.newslist.pagination.NewsDataSource
import com.aqube.newsapp.ui.newslist.pagination.NewsDataSourceFactory
import com.aqube.newsapp.util.PAGE_SIZE
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * NewsListViewModel is used to get the data from repository and pass the data in the form of LiveData to view
 */
class NewsListViewModel @Inject constructor(private val repository: NewsRepository) :
    BaseViewModel() {

    private val newsDataSourceFactory: NewsDataSourceFactory
    private val compositeDisposable = CompositeDisposable()

    var newsList: LiveData<PagedList<News>>

    /**
     * Init creating the NewsDataSource object and setting up the NewsDataSourceFactory
     * Setting Pagination library to load news based on page size and other params
     */
    init {
        newsDataSourceFactory = NewsDataSourceFactory(compositeDisposable, repository)
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
        newsList = LivePagedListBuilder<Int, News>(newsDataSourceFactory, config).build()
    }

    /**
     * getState is used to set the network status like loading fail or loaded
     */
    fun getState(): LiveData<NetworkState> =
        Transformations.switchMap<NewsDataSource, NetworkState>(
            newsDataSourceFactory.newsDataSourceLiveData,
            NewsDataSource::networkState
        )

    /**
     * Called when the ViewModel is being destroyed we are disposing all the RX subscription
     */
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}