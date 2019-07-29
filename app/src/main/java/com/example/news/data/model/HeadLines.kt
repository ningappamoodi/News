package com.example.news.data.model

data class HeadLines(val articles: List<Articles>)

data class Articles(val title: String, val description: String, val url:String, val urlToImage:String)