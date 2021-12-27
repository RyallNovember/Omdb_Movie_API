package com.ryall.cartrack.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "movies"
)
data class Search(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    val Poster: String,
    @ColumnInfo(name = "title") val Title: String,
    @ColumnInfo(name = "type") val Type: String,
    @ColumnInfo(name = "year") val Year: String)