package com.example.news.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.news.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        newsDetailsView.loadUrl(intent.getStringExtra("url"))
    }
}
