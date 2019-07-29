package com.example.news.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.dao.HeadLinesDao
import com.example.news.db.entity.HeadlinesEntity

@Database(entities = [HeadlinesEntity::class], version = 4)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun headLinesDao(): HeadLinesDao
}