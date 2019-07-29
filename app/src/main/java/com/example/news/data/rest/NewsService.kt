package com.example.news.data.rest

import com.example.news.data.model.HeadLines
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country:String = "us",
                        @Query("apiKey") apiKey:String = "8c972407f91243129a128ca18354eae6")
            : Single<HeadLines>
}