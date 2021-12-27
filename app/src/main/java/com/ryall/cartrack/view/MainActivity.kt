package com.ryall.cartrack.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryall.cartrack.adapters.CustomAdapter
import com.ryall.cartrack.databinding.ActivityMainBinding
import com.ryall.cartrack.models.Search
import com.ryall.cartrack.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CustomAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by viewModels()
    private var type = ""
    private var title = ""
    private var enableSearchButton = false
    private var data : List<Search>? = null

    private lateinit var customAdapter: CustomAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        binding.btnSearch.setOnClickListener {
            when {
                binding.rbMovies.isChecked -> {
                    type = "movie"
                    enableSearchButton = true
                }
                binding.rbSeries.isChecked -> {
                    type = "series"
                    enableSearchButton = true
                }
                else -> {
                    enableSearchButton = false
                }
            }
            if (!enableSearchButton) {
                Toast.makeText(this, "Please choose search type", Toast.LENGTH_SHORT).show()
            } else if (binding.etTitle.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter title", Toast.LENGTH_SHORT).show()
            } else {
                title = binding.etTitle.text.toString().lowercase()
                viewModel.makeApiCall(type, title)
                binding.progressbar.visibility = View.VISIBLE
                viewModel.getAllSavedMovies().observe(this, {
                    if (it.isEmpty()){
                        binding.progressbar.visibility = View.GONE
                        binding.frame.visibility = View.VISIBLE
                        Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show()
                        return@observe
                    } else{
                        data = it
                        customAdapter.setListData(data)
                        customAdapter.notifyDataSetChanged()
                        binding.rvMovies.visibility = View.VISIBLE
                        binding.frame.visibility = View.GONE
                    }
                })
            }
        }
    }

    override fun onBackPressed() {
        if (binding.rvMovies.visibility == View.GONE) {
            super.onBackPressed()
        } else {
            binding.rvMovies.visibility = View.GONE
            binding.frame.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(position: Int) {
        val movieItem = data?.get(position)
        val send = Intent(this@MainActivity, MovieDetailActivity::class.java)
        val b = Bundle()
        b.putString("poster", movieItem?.Poster)
        b.putString("title", movieItem?.Title)
        b.putString("year", movieItem?.Year)
        send.putExtras(b)
        startActivity(send)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        customAdapter = CustomAdapter(this)
        binding.rvMovies.apply {
            adapter = customAdapter
            layoutManager = LinearLayoutManager(context)
            visibility = View.VISIBLE
        }
        viewModel.getAllSavedMovies().observe(this, {
            data = it
            customAdapter.setListData(data)
            customAdapter.notifyDataSetChanged()
            if (customAdapter.itemCount > 0) {
                binding.frame.visibility = View.GONE
                binding.progressbar.visibility = View.GONE
            }
        })
    }
}