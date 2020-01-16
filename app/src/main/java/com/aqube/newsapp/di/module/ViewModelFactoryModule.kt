package com.aqube.newsapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.aqube.newsapp.base.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}