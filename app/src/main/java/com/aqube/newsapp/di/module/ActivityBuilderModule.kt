package com.aqube.newsapp.di.module

import com.aqube.newsapp.di.newslist.NewsListModule
import com.aqube.newsapp.di.newslist.NewsListViewModelModule
import com.aqube.newsapp.ui.newsdetail.NewsDetailActivity
import com.aqube.newsapp.ui.newslist.NewsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [NewsListViewModelModule::class, NewsListModule::class])
    abstract fun contributeNewsListActivity(): NewsListActivity

    @ContributesAndroidInjector()
    abstract fun contributeNewsDetailActivity(): NewsDetailActivity
}