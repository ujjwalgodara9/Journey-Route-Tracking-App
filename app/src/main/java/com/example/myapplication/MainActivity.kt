package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    // Your main app composition goes here
    Scaffold(
        topBar = { TopAppBar(title = { Text("Journey Tracker") },modifier= Modifier.fillMaxWidth()) }
    ) { padding ->
        JourneyScreen(modifier = Modifier.padding(padding).fillMaxWidth())
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JourneyScreen(modifier: Modifier = Modifier) {
    var totalDistance by remember { mutableStateOf(0.0) } // Example: 100.0 km for the whole journey
    var distanceWalked by remember { mutableStateOf(0.0) }
    var distanceToAdd by remember { mutableStateOf(0.0) }
    var unit by remember { mutableStateOf("km") } // "km" for kilometers, "mi" for miles
    var stopsList by remember { mutableStateOf(listOf<Stop>()) }

    // Conversion lambda
//    val convertDistance = { distance: Double, unit: String ->
//        if (unit == "km") distance else distance/ 0.621371
//    }

//    fun convertDistance(distance: Double, unit: String): Double {
//        val conversionFactor = if (unit == "km") 1.0 else 0.621371  // 1.0 for no conversion (km), 0.621371 for miles to km
//        return distance * conversionFactor
//    }

    // Conversion functions
    fun convertToKm(distance: Double, currentUnit: String): Double {
        return if (currentUnit == "mi") distance * 1.60934 else distance
    }

    fun convertToMiles(distance: Double, currentUnit: String): Double {
        return if (currentUnit == "km") distance * 0.621371 else distance
    }

    // Function to convert distance based on the new unit
    fun convertToUnit(distance: Double, newUnit: String): Double {
        return if (newUnit == "km") convertToMiles(distance, unit) else convertToKm(distance, unit)
    }

//    fun updateUnit(newUnit: String) {
//        if (unit != newUnit) {
//            unit = newUnit
//            totalDistance = if (unit == "mi") convertToMiles(totalDistance, "km") else totalDistance
//            distanceWalked = if (unit == "mi") convertToMiles(distanceWalked, "km") else distanceWalked
//            stopsList = stopsList.map { stop ->
//                stop.copy(
//                    distanceFromLastStop = convertToUnit(stop.distanceFromLastStop, unit),
//                    totalDistanceTraveled = convertToUnit(stop.totalDistanceTraveled, unit),
//                    distanceLeftToDestination = convertToUnit(stop.distanceLeftToDestination, unit),
//                    unit = unit
//                )
//            }
//        }
//    }





    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Spacer(modifier = Modifier.height(60.dp)) // Adjust spacing as needed
        TextField(
            value = totalDistance.toString(),
            onValueChange = { totalDistance = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Total Distance") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp)) // Adjust spacing as needed
        TextField(
            value = distanceToAdd.toString(),
            onValueChange = { distanceToAdd = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Distance from Last Stop") },
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(15.dp)) // Adjust spacing as needed
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier.fillMaxWidth()
         ) {
            Button(onClick = {
                // Update distances and add a stop
                distanceWalked += distanceToAdd
                var distanceLeft = totalDistance - distanceWalked
                distanceLeft = totalDistance - distanceWalked
                stopsList = stopsList + Stop(
                    name = "Stop ${stopsList.size + 1}",
                    distanceFromLastStop = convertToUnit(distanceToAdd, unit),
                    totalDistanceTraveled = convertToUnit(distanceWalked, unit),
                    distanceLeftToDestination = convertToUnit(distanceLeft,unit), // Use the calculated value here
                    unit = unit
                )

                distanceToAdd = 0.0 // Reset after adding


            }) {
                Text("Add Stop")
                Spacer(modifier = Modifier.height(20.dp)) // Adjust spacing as needed
            }
            Button(onClick = {
                // Toggle unit
                unit = if (unit == "km") "mi" else "km"
                // Convert existing distances
                var newtotalDistance = convertToUnit(totalDistance, unit)
                distanceWalked = convertToUnit(distanceWalked, unit)
                //val distanceLeft = totalDistance - distanceWalked
                stopsList = stopsList.map { stop ->
                    stop.copy(
                        distanceFromLastStop = convertToUnit(stop.distanceFromLastStop, unit),
                        totalDistanceTraveled = convertToUnit(stop.totalDistanceTraveled, unit),
                        distanceLeftToDestination = convertToUnit(newtotalDistance - distanceWalked, unit), // Calculate here
                        unit = unit
                    )
                }


            }) {
                Text("Switch to ${if (unit == "km") "Miles" else "Kilometers"}")
                Spacer(modifier = Modifier.height(20.dp)) // Adjust spacing as needed


            }

        }

        Spacer(modifier = Modifier.height(20.dp)) // Adjust spacing as needed

        Text(text = "Total Distance Walked: ${distanceWalked} $unit", modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
        Text(text = "Distance Left: ${totalDistance - distanceWalked} $unit",modifier = Modifier.fillMaxWidth(), textAlign=TextAlign.Center)

        Spacer(modifier = Modifier.height(20.dp)) // Adjust spacing as needed

        // Display journey progress
        val progress = if (totalDistance > 0) distanceWalked / totalDistance else 0.0
        LinearProgressIndicator(progress = progress.toFloat(), modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(20.dp)) // Adjust spacing as needed

        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                // Column for stop information headers
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Stop Name",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add spacing between elements
                    Text(
                        text = "From Last Stop",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add spacing between elements
                    Text(
                        text = "Total Distance",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
//                    Spacer(modifier = Modifier.width(8.dp)) // Add spacing between elements
//                    Text(
//                        text = "Left to Destination",
//                        modifier = Modifier.weight(1f),
//                        textAlign = TextAlign.Center
//                    )
                }
                Spacer(modifier = Modifier.height(20.dp)) // Adjust spacing as needed
            }
            items(stopsList) { stop ->
                // Row for each stop's information
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = stop.name, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.width(8.dp)) // Add spacing between elements
                    Text(text = "${stop.distanceFromLastStop} ${if (unit == "km") "mi" else "km"}", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.width(8.dp)) // Add spacing between elements
                    Text(text = "${stop.totalDistanceTraveled} ${if (unit == "km") "mi" else "km"}", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
//                    Spacer(modifier = Modifier.width(8.dp)) // Add spacing between elements
//                    Text(text = "${stop.distanceLeftToDestination} ${if (unit == "km") "mi" else "km"}", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                }

                    Spacer(modifier = Modifier.height(15.dp)) // Adjust spacing as needed

            }

        }
    }
}

