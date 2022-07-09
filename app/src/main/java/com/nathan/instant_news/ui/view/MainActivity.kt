package com.nathan.instant_news.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
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