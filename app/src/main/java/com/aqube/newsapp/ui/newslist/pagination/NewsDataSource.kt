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

class NewsDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val repository: NewsRepository
) : PageKeyedDataSource<Int, News>() {

    var networkState: MutableLiveData<NetworkState> = MutableLiveData()

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

    private fun updateState(state: NetworkState) {
        this.networkState.postValue(state)
    }

    private fun loadMore(totalRecord: Int, currentPage: Int): Boolean {
        val totalPage = (totalRecord / PAGE_SIZE)
        return totalPage > currentPage
    }
}