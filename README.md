# Journey Route Tracking App

## Overview
This app is designed to show users detailed information about their journey, including the stops, distances between the stops, and the progress of the journey. The app features functionalities such as switching distance units between kilometers and miles, indicating when the next stop is reached, and displaying journey progress with a progress bar.

## Developer
Ujjwal Godara, IIIT Delhi Student, Mobile Computing Course 2024

## Features
- **Distance Unit Conversion:** Users can switch between kilometers and miles using a dedicated button.
- **Next Stop Indication:** A button allows users to indicate that they have reached the next stop.
- **Journey Progress Tracking:**
  - Textboxes show each stop and their respective distances.
  - Displays total distance covered and total distance left.
  - Includes a progress bar to visually indicate the journey progress.
- **Lazy List for Stops:** For routes with more than 10 stops, the app uses a lazy list to efficiently display the data. Two hardcoded entries demonstrate the usage of both a normal list and a lazy list.
- **Compatibility:** The app runs on both Android devices and the Android emulator.

## Implementation Details
- **Jetpack Compose:** The UI is implemented using Jetpack Compose for modern, declarative UI development.
- **Lazy Column:** Utilized for efficiently displaying a large number of stops.
- **Buttons for Interaction:**
  - **Unit Conversion Button:** Toggles between displaying distances in kilometers and miles.
  - **Next Stop Button:** Updates the progress to indicate the user has reached the next stop.
- **Progress Tracking:**
  - Textboxes display each stop, the distance between stops, total distance covered, and the total distance left.
  - A progress bar visually represents the user's progress through the journey.
- **User Interface:** Designed to be intuitive and user-friendly, ensuring a smooth user experience with clear and responsive elements.

## Installation
1. Clone the repository:

   git clone https://github.com/ujjwalgodara9/journey-route-tracking-app
