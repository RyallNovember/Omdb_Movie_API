package com.ryall.cartrack.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ryall.cartrack.R
import com.ryall.cartrack.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val poster = intent.getStringExtra("poster")
        val year = intent.getStringExtra("year")

        binding.tvTitle.text = title
        binding.tvYear.text = year

        if (poster.equals("N/A")){
            binding.ivPoster.setImageResource(R.mipmap.no_image)
        }else{
            Glide.with(this).load(poster).into(binding.ivPoster)

        }
    }
}