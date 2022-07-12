package com.nathan.instant_news.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nathan.instant_news.R
import com.nathan.instant_news.data.model.News
import com.nathan.instant_news.databinding.ActivityMainBinding
import com.nathan.instant_news.ui.adapter.NewsAdapter
import com.nathan.instant_news.ui.viewmodel.MainStateEvent
import com.nathan.instant_news.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.nathan.instant_news.ui.viewmodel.MainViewModel

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: NewsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* Bind the view to the activity */
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /* Trigger an event to get the news data */
        mainViewModel.setStateEvent(MainStateEvent.GetNewsEvents)

        setupUI()
        setupObserver()
    }

    /**
     * Setup the RecyclerView adapter
     */
    private fun setupUI() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
        supportActionBar?.setCustomView(R.layout.toolbar_layout);
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(arrayListOf())
        setupItemsListener(adapter)
        binding.recyclerView.adapter = adapter
        // Adding RecyclerView custom divider
        val itemDecoration = DividerItemDecoration(
            binding.recyclerView.context,
            (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.layer, null))
        binding.recyclerView.addItemDecoration(itemDecoration)
    }

    /**
     * Listen news list items click
     */
    private fun setupItemsListener(adapter: NewsAdapter) {
        adapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(binding.recyclerView.context, NewsDetail::class.java)
                intent.putExtra("news", adapter.getNews(position))
                startActivity(intent)
            }
        })
    }

    /**
     * Observe the ViewModel dataState to be informed we data is loading, arrived or of an error
     */
    private fun setupObserver() {
        mainViewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<List<News>> -> {
                    binding.progressBar.visibility = View.GONE
                    if (dataState.data.isNotEmpty()) adapter.addData(ArrayList(dataState.data))
                    binding.recyclerView.visibility = View.VISIBLE
                }
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    dataState.exception.message?.let { Log.e("Error", it) }
                    Toast.makeText(this, dataState.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}