package com.ryall.cartrack.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.ryall.cartrack.api.ApiService
import com.ryall.cartrack.db.AppDao
import com.ryall.cartrack.models.MovieResponse
import com.ryall.cartrack.models.Search
import com.ryall.cartrack.util.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val appDao: AppDao
) {
    fun getAllRecords(): LiveData<List<Search>> {
        return appDao.getAllRecords()
    }

    fun insertMovieRecord(movieData: Search) {
        appDao.insertRecords(movieData)
    }

    fun makeApiCall(type: String, title: String) {
        val call: Call<MovieResponse> = apiService.getMovieData(type, title, API_KEY)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    appDao.deleteAllRecords()
                    response.body()?.Search?.forEach {
                        insertMovieRecord(it)
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("TAG", t.message!!)
            }
        })
    }
}

