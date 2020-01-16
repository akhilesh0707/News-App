package com.aqube.newsapp.ui.newslist

import com.aqube.newsapp.base.network.ResponseHandler
import com.aqube.newsapp.di.network.auth.NewsListApi
import com.aqube.newsapp.model.NewsResponse
import com.aqube.newsapp.util.API_KEY
import com.aqube.newsapp.util.COUNTRY
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepository @Inject constructor() {

    @Inject
    lateinit var newsListApi: NewsListApi

    fun getNewsList(
        pageSize: Int,
        currentPage: Int,
        responseHandler: ResponseHandler<NewsResponse>,
        compositeDisposable: CompositeDisposable
    ) {

        newsListApi.getNewsList(
            pageSize,
            currentPage,
            COUNTRY,
            API_KEY
        ).toObservable().subscribeOn(Schedulers.io())
            .subscribe(object : io.reactivex.Observer<NewsResponse> {
                override fun onSubscribe(disposable: Disposable) {
                    compositeDisposable.add(disposable)
                }

                override fun onNext(newsResponse: NewsResponse) {
                    responseHandler.onRequestSuccess(newsResponse)
                }

                override fun onError(e: Throwable) {
                    responseHandler.onRequestFailure(e.message)
                }

                override fun onComplete() {
                }
            })
    }
}