package com.example.news.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.cache.ImageLoader
import com.example.news.db.entity.HeadlinesEntity
import com.example.news.view.main.DetailsActivity
import kotlinx.android.synthetic.main.headlines_item.view.*


class HeadLinesAdapter : RecyclerView.Adapter<HeadLinesAdapter.HeadLinesViewHolder> {

    private val context: Context
     var headlinesList: List<HeadlinesEntity>

    val imageLoader:ImageLoader

    constructor(context: Context, headlinesList: List<HeadlinesEntity>) : super() {
        this.context = context
        this.headlinesList = headlinesList
        imageLoader = ImageLoader(context)
    }

    class HeadLinesViewHolder :
        RecyclerView.ViewHolder, View.OnClickListener{

        val context: Context
        private val view: View
        private val imageLoader: ImageLoader

        lateinit var headlinesEntity: HeadlinesEntity

        constructor(context: Context, view: View, imageLoader: ImageLoader) : super(view) {
            this.context = context
            this.view = view
            this.imageLoader = imageLoader
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

            newsDetails(headlinesEntity.url!!)
        }

        fun bind(headlinesEntity: HeadlinesEntity) {
            this.headlinesEntity = headlinesEntity

            view.newsTitle.text = headlinesEntity.title
           view.newsDescription.setText(headlinesEntity.description)

            imageLoader.displayImage(headlinesEntity.urlToImage, view.newsImage)
        }

        private fun newsDetails(url: String) {

            val details: Intent = Intent(context, DetailsActivity::class.java)
            details.putExtra("url", url)
            context.startActivity(details)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadLinesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.headlines_card, parent, false)
       return HeadLinesViewHolder(context, view, imageLoader)
    }

    override fun getItemCount(): Int {
        return headlinesList.size
    }

    override fun onBindViewHolder(holder: HeadLinesViewHolder, position: Int) {
       holder.bind(headlinesList[position])
    }

}