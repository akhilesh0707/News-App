package com.aqube.newsapp.di.newslist

import com.aqube.newsapp.di.network.auth.NewsListApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NewsListModule {

    @Provides
    fun provideNewsListApi(retrofit: Retrofit): NewsListApi {
        return retrofit.create(NewsListApi::class.java)
    }
}