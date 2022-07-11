package com.nathan.instant_news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nathan.instant_news.data.model.News
import com.nathan.instant_news.databinding.ItemLayoutBinding
import java.util.*


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
            binding.textViewTitle.text = news.title
            val date = getFormattedDate(news.publishedAt)
            binding.textViewDate.text = date
            binding.textViewSource.text = news.source.name
            if (news.urlToImage != null) {
                Glide.with(binding.imageViewPicture.context)
                    .load(news.urlToImage)
                    .into(binding.imageViewPicture)
            }
        }

        /**
         * Get the number of minutes, hours or days between now and the date param
         */
        private fun getFormattedDate(date: Date): String {
            val now = Date()
            val diff: Long = now.time - date.time
            val minutes = (diff / (1000 * 60))
            val hours = minutes/60
            val days = hours/24
            return when {
                days > 0 -> {
                    "Il y a $days " + (if (days > 1) "jours" else "jour")
                }
                hours > 0 -> {
                    "Il y a $hours " + (if (hours > 1) "heures" else "heure")
                }
                else -> {
                    "Il y a $minutes " + (if (minutes > 1) "minutes" else "minute")
                }
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