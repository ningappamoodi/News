package com.example.news.di.module

import android.content.Context
import com.example.news.data.rest.NewsService
import com.example.news.db.DBRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Module
class ApplicationModule {

    private val BASEURL = "https://newsapi.org/v2/"

    @Singleton
    @Provides
    fun  provideRetrofit() : Retrofit {
        return  Retrofit.Builder().baseUrl(BASEURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun  provideNewsService(retrofit: Retrofit) : NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Singleton
    @Provides
    fun  provideDBRepository(applicationContext: Context) : DBRepository {
        return DBRepository(applicationContext)
    }
}
