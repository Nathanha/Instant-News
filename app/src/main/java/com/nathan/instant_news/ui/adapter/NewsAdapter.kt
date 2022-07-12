package com.nathan.instant_news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nathan.instant_news.data.model.News
import com.nathan.instant_news.databinding.ItemLayoutBinding
import com.nathan.instant_news.utils.Utils
import java.util.*

/**
 * Adapter class to populate RecyclerView with data
 */
class NewsAdapter(
    private val news: ArrayList<News>
) : RecyclerView.Adapter<NewsAdapter.DataViewHolder>() {

    private lateinit var adapterListener: OnItemClickListener

    /**
     * Interface to define a click listener on the RecyclerView items
     */
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    /**
     * Init the class click listener
     */
    fun setOnItemClickListener(listener: OnItemClickListener) {
        adapterListener = listener
    }

    /**
     * Class to bind news data to the ItemLayout view
     */
    class DataViewHolder(private val binding: ItemLayoutBinding, listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {

        init {
            // Set the RecyclerView onClickListener
            binding.container.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun bind(news: News) {
            binding.textViewTitle.text = news.title
            val date = Utils.getFormattedDateForDisplay(news.publishedAt)
            binding.textViewDate.text = date
            binding.textViewSource.text = news.source.name
            if (news.urlToImage != null) {
                Glide.with(binding.imageViewPicture.context)
                    .load(news.urlToImage)
                    .into(binding.imageViewPicture)
            }
        }
    }

    /**
     * Create the DataViewHolder to bind news data to the ItemLayout view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding, adapterListener)
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

    /**
     * Get a specific News object with its position
     */
    fun getNews(position: Int): News {
        return news[position]
    }

}