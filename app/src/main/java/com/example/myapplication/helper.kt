//package com.example.myapplication
//
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun RouteTrackingApp() {
//    var distanceInKm by remember { mutableStateOf(0.0) }
//    var distanceInMiles by remember { mutableStateOf(0.0) }
//    var stopsReached by remember { mutableStateOf(0) }
//    var totalStops by remember { mutableStateOf(10) }
//    var stopsList by remember { mutableStateOf(generateStopsList(totalStops)) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        // Distance Display
//        Text("Distance: $distanceInKm km / $distanceInMiles miles")
//
//        // Next Stop Button
//        Button(onClick = { onReachedNextStop() }) {
//            Text("Reached Next Stop")
//        }
//
//        // Progress Display
//        ProgressDisplay(stopsList, stopsReached, totalStops)
//
//        // Lazy List of Stops
//        LazyColumn {
//            items(stopsList.size) { index ->
//                Text("Stop ${index + 1}: ${stopsList[index]}")
//            }
//        }
//    }
//}
//
//@Composable
//fun ProgressDisplay(stopsList: List<String>, stopsReached: Int, totalStops: Int) {
//    // Calculate total distance, distance covered, and distance left
//    val totalDistance = calculateTotalDistance(stopsList)
//    val distanceCovered = calculateDistanceCovered(stopsList, stopsReached)
//    val distanceLeft = totalDistance - distanceCovered
//
//    // Display progress information
//    Text("Total Distance: $totalDistance")
//    Text("Distance Covered: $distanceCovered")
//    Text("Distance Left: $distanceLeft")
//
//    // Progress Bar
//    LinearProgressIndicator(
//        progress = (stopsReached.toFloat() / totalStops.toFloat())
//    )
//}
//
//// Helper functions for distance calculation and stop list generation
//fun calculateTotalDistance(stopsList: List<String): Double {
//    // Calculate total distance based on stops
//    return 0.0
//}
//
//fun calculateDistanceCovered(stopsList: List<String>, stopsReached: Int): Double {
//    // Calculate distance covered based on stops reached
//    return 0.0
//}
//
//fun generateStopsList(totalStops: Int): List<String> {
//    // Generate a list of stops based on total stops
//    return listOf()
//}
//
//class helper {
//
//
//}
//
