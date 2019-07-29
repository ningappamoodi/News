package com.example.news.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.news.data.model.HeadLines
import com.example.news.db.DBRepository
import com.example.news.db.entity.HeadlinesEntity
import com.example.news.di.module.ApplicationModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import io.reactivex.Completable
import io.reactivex.functions.Action
import javax.inject.Inject



class  HeadLinesViewModel @Inject constructor() : ViewModel() {

     var  headLinesLiveData: LiveData<List<HeadlinesEntity>>? = null


    var dbRepo: DBRepository? = null

    fun loadData() {


        val headLinesDao = dbRepo?.getDB()?.headLinesDao()

        val topHeadLinesLiveData = headLinesDao?.getTopHeadLines()
        headLinesLiveData = topHeadLinesLiveData

        loadNetworkData()
    }
    fun loadNetworkData() {

        val appModule = ApplicationModule()

        val topHeadlinesSingle = appModule.provideNewsService(
            appModule.provideRetrofit()).getTopHeadlines()

        topHeadlinesSingle.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<HeadLines>() {
            override fun onSuccess(headLines: HeadLines?) {
              Log.i("NEWS", "##Inside onSuccess")

                val headLinesEntityList = mutableListOf<HeadlinesEntity>()

                headLines?.let {

                    for(article in headLines.articles) {

                        val headlinesEntity =
                            HeadlinesEntity(article.title, article.description, article.url, article.urlToImage)
                        headLinesEntityList.add(headlinesEntity)
                    }
                }

                insertArticles(headLinesEntityList)

                Log.i("NEWS", "##Insertion completed!")
            }

            override fun onError(e: Throwable?) {
                Log.i("NEWS", "##Inside onError")
                e?.printStackTrace()
            }

        })
    }

    private fun insertArticles(headLinesEntityList: MutableList<HeadlinesEntity>) {
        viewModelScope.launch {
            dbRepo?.getDB()?.headLinesDao()?.deleteAndCreateHeadLines(headLinesEntityList)
        }
    }
}