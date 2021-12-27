package com.ryall.cartrack.models

data class MovieResponse(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)