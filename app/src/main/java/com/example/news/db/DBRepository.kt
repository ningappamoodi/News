package com.example.news.db

import android.content.Context
import androidx.room.Room
import javax.inject.Inject

class DBRepository @Inject constructor(private val applicationContext: Context) {

    fun getDB() = Room.databaseBuilder(applicationContext, NewsDatabase::class.java, "db-news")
            .fallbackToDestructiveMigration().build()
}