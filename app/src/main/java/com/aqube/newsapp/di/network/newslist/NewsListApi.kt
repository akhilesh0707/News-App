package com.aqube.newsapp.di.network.auth

import com.aqube.newsapp.model.NewsResponse
import com.aqube.newsapp.util.GET_TOP_HEADINGS
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsListApi {

    @GET(GET_TOP_HEADINGS)
    fun getNewsList(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Flowable<NewsResponse>

}