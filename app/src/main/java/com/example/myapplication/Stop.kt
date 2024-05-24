package com.example.myapplication

data class Stop(
    val name: String,
    val distanceFromLastStop: Double,
    val totalDistanceTraveled: Double,
    val distanceLeftToDestination: Double, // Add this property
    val unit: String
)
