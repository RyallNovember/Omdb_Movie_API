package com.ryall.cartrack.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ryall.cartrack.models.Search

@Dao
interface AppDao {
    @Query("SELECT * FROM movies")
    fun getAllRecords(): LiveData<List<Search>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(movieData: Search)

    @Query("DELETE FROM movies")
    fun deleteAllRecords()
}