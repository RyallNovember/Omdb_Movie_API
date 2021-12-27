package com.ryall.cartrack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ryall.cartrack.models.Search
import com.ryall.cartrack.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject
constructor(private val repository: MovieRepository) : ViewModel() {
    fun getAllSavedMovies() : LiveData<List<Search>>{
        return repository.getAllRecords()
    }

    fun makeApiCall(type: String, title:String){
        repository.makeApiCall(type, title )
    }
}