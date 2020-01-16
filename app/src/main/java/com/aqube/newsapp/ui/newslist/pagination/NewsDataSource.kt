package com.aqube.newsapp.ui.newslist.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.aqube.newsapp.base.NetworkState
import com.aqube.newsapp.base.network.ResponseHandler
import com.aqube.newsapp.model.News
import com.aqube.newsapp.model.NewsResponse
import com.aqube.newsapp.ui.newslist.NewsRepository
import com.aqube.newsapp.util.FIRST_PAGE
import com.aqube.newsapp.util.PAGE_SIZE
import io.reactivex.disposables.CompositeDisposable

/**
 * NewsDataSource is used to load the data based on current page size and managing the load more data from repository api
 */
class NewsDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val repository: NewsRepository
) : PageKeyedDataSource<Int, News>() {

    var networkState: MutableLiveData<NetworkState> = MutableLiveData()

    /**
     * When loading first time
     * @param params : we can get the params like number of page other detail
     * @param callback : is used to return the data and pass the current page and key to load more
     */
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, News>
    ) {

        updateState(NetworkState.LOADING)

        repository.getNewsList(
            PAGE_SIZE,
            FIRST_PAGE,
            object : ResponseHandler<NewsResponse> {
                override fun onRequestFailure(errorMessage: String?) {
                    updateState(NetworkState(NetworkState.Status.FAILED, errorMessage))
                }

                override fun onRequestSuccess(model: NewsResponse) {
                    callback.onResult(model.news, null, FIRST_PAGE + 1)
                    updateState(NetworkState.LOADED)
                }
            }, compositeDisposable
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {}

    /**
     * Load after is used to load more data when user is scrolled the page
     * @param params : Get the parameters like current page number and other detail
     * @param callback : is used to return the data and pass the current page and key to load more
     */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        val currentCount = params.key
        updateState(NetworkState.LOADING)
        repository.getNewsList(
            PAGE_SIZE,
            currentCount,
            object : ResponseHandler<NewsResponse> {
                override fun onRequestFailure(errorMessage: String?) {
                    updateState(NetworkState(NetworkState.Status.FAILED, errorMessage))
                }

                override fun onRequestSuccess(model: NewsResponse) {
                    val currentCount = params.key
                    var key: Int? = null
                    if (loadMore(model.totalResults, currentCount)) {
                        key = currentCount + 1;
                    }
                    callback.onResult(model.news, key)
                    updateState(NetworkState.LOADED)
                }
            }, compositeDisposable
        )
    }

    /**
     * Updating the network status on LiveData
     * @param state : NetworkState loaded, loading or failed
     */
    private fun updateState(state: NetworkState) {
        this.networkState.postValue(state)
    }

    /**
     * Check is last page is loaded or not
     * @param totalRecord : Total number of news
     * @param currentPage : Current page load
     * @return is loading required or not
     */
    private fun loadMore(totalRecord: Int, currentPage: Int): Boolean {
        val totalPage = (totalRecord / PAGE_SIZE)
        return totalPage > currentPage
    }
}