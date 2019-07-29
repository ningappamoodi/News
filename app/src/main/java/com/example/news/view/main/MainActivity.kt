package com.example.news.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.db.DBRepository
import com.example.news.db.entity.HeadlinesEntity
import com.example.news.view.adapter.HeadLinesAdapter
import com.example.news.viewmodel.HeadLinesViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val headLinesViewModel = ViewModelProviders.of(this).get(HeadLinesViewModel::class.java)
        headLinesViewModel.dbRepo = DBRepository(this)

        headLinesViewModel.loadData()

        val adapter = HeadLinesAdapter(this, mutableListOf())
        headLinesRecylerView.adapter = adapter
        headLinesRecylerView.layoutManager = LinearLayoutManager(this)

        headLinesViewModel.headLinesLiveData?.observe(this,
            Observer<List<HeadlinesEntity>> { t ->
                adapter.headlinesList = t ?: mutableListOf()
                adapter.notifyDataSetChanged()
            })

    }


}
