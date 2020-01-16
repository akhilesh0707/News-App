package com.aqube.newsapp.di.module

import com.aqube.newsapp.R
import com.aqube.newsapp.base.MyApplication
import com.aqube.newsapp.util.BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(httpClient: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient.Builder {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        return httpClient
    }

    @Singleton
    @Provides
    fun provideRequestOption(): RequestOptions {
        return RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        application: MyApplication,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

}