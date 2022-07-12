package com.nathan.instant_news.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nathan.instant_news.R
import com.nathan.instant_news.data.model.News
import com.nathan.instant_news.databinding.ActivityNewsDetailBinding
import java.text.SimpleDateFormat
import java.util.*

class NewsDetail : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        /* Bind the view to the activity */
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupUI()
    }

    /**
     * Bind news data to the view
     */
    private fun setupUI() {
        supportActionBar?.hide()
        val news = intent.getSerializableExtra("news") as News

        if (news.urlToImage != null) {
            Glide.with(binding.image.context)
                .load(news.urlToImage)
                .into(binding.image)
        }
        binding.title.text = news.title
        binding.description.text = news.description
        binding.content.text = news.content
        binding.date.text = getFormattedDate(news.publishedAt)
        binding.showArticle.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
            startActivity(browserIntent)
        }
    }

    /**
     * Format the date to display in the view
     */
    private fun getFormattedDate(date: Date): String {
        val dt = SimpleDateFormat("dd/MM/yyyy - hh:mm")
        return dt.format(date)
    }
}