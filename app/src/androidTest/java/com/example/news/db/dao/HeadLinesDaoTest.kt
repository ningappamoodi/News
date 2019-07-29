package com.example.news.db.dao


import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.news.db.NewsDatabase
import com.example.news.db.entity.HeadlinesEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HeadLinesDaoTest {

   private lateinit var  newsDatabase: NewsDatabase
    @Before
    fun initDb() {

         newsDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            NewsDatabase::class.java
        ).build()
    }

    @After
    fun closeDB() {
        newsDatabase.close()
    }

    @Test
    fun testInsertAll()  {

        runBlocking {
            val articles = mutableListOf<HeadlinesEntity>()

            val headlinesEntity = HeadlinesEntity("title", "desc", "url", "urltoimage")

            articles.add(headlinesEntity)
            newsDatabase.headLinesDao().insertHeadLines(articles)

            val topHeadLines = newsDatabase.headLinesDao().getTopHeadLines()

            assertNotNull(topHeadLines)
        }
    }
}