package com.nathan.instant_news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nathan.instant_news.data.model.News
import com.nathan.instant_news.databinding.ItemLayoutBinding

/**
 * Adapter class to populate RecyclerView with data
 */
class NewsAdapter(
    private val news: ArrayList<News>
) : RecyclerView.Adapter<NewsAdapter.DataViewHolder>() {

    /**
     * Class to bind news data to the ItemLayout view
     */
    class DataViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.textViewUserName.text = news.title
            binding.textViewUserEmail.text = news.author
            if (news.urlToImage != null) {
                Glide.with(binding.imageViewAvatar.context)
                    .load(news.urlToImage)
                    .into(binding.imageViewAvatar)
            }
        }
    }

    /**
     * Create the DataViewHolder to bind news data to the ItemLayout view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    /**
     * Get the number of news in the list
     */
    override fun getItemCount(): Int = news.size

    /**
     * Bind data to the ViewHolder
     */
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(news[position])

    /**
     * Add data to the list
     */
    fun addData(list: List<News>) {
        news.addAll(list)
    }

}