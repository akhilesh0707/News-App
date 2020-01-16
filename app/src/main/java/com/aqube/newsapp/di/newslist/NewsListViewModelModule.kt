package com.aqube.newsapp.di.newslist

import androidx.lifecycle.ViewModel
import com.aqube.newsapp.di.ViewModelKey
import com.aqube.newsapp.ui.newslist.NewsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewsListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun bindNewsListViewModel(newsListViewModel: NewsListViewModel): ViewModel
}