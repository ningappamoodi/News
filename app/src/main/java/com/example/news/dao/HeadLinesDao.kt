package com.example.news.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.news.db.entity.HeadlinesEntity

@Dao
interface HeadLinesDao {

    @Query("SELECT * FROM HeadlinesEntity")
    fun getTopHeadLines(): LiveData<List<HeadlinesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeadLines(articles: List<HeadlinesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeadLine(article: HeadlinesEntity)

    @Query("DELETE FROM HeadlinesEntity")
  suspend  fun cleanHeadLines()

    @Transaction
   suspend fun deleteAndCreateHeadLines(users: List<HeadlinesEntity>) {
        cleanHeadLines()
        insertHeadLines(users)
    }


}