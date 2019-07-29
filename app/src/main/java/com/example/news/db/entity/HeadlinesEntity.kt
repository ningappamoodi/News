package com.example.news.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HeadlinesEntity(@ColumnInfo(name = "title") val title:String?,
                           @ColumnInfo(name = "description") val description:String?,
                           @ColumnInfo(name = "url") val url:String?,
                           @ColumnInfo(name = "urlToImage") val urlToImage: String?) {

    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}