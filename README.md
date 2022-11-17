# City-Weather
An android app that allows one to search for weather forecase for any city.Built following MVVM and clean architecture principles. 
The app fetches weather forecast data for a given city then displays the forecast for the current day and two days after the current day.

## APK 
To test the apk you can download the apk from [here](https://github.com/agesa3/City-Weather/blob/main/demo/weather%20app.apk) and install it on your android device.


## Setup
To run the project you will first get your API key from [openweather](https://openweathermap.org/).Then add it to your <b>local.properties</b> file i.e API_KEY=YOUR_API_KEY

## Additional features to be worked on:
 - Add more UI Test.
 - Rewrite the UI to use Jetpack Compose.
 - Add Baseline Profiles
 - Play around with kotlin-dsl
 


## Test 
To run the tests,run this gradle command
```sh
./gradlew test
```

# Architecture
Clean Architecture with MVVM

# Libraries & Jepack components used
- Retrofit
- Navigation Component
- Room DB
- Kotlin Coroutines
- Dagger Hilt for Dependancy Injection
- Truth Library
- ViewModel


# Demo
<img src="https://github.com/agesa3/City-Weather/blob/main/screenshots/home.png" width="240"/> <img src="https://github.com/agesa3/City-Weather/blob/main/screenshots/detail.png" width="240"/> 


