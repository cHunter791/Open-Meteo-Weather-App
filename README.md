# Open Meteo Weather App

An Android Application that uses the Open Meteo Weather API to load forecasts for locations

## Setup

To get setup with this project follow the below steps:

* Ensure you are running Android Studio Hedgehog | 2023.1.1 or higher
* Clone or download the project
* In the root directory create a `secrets.properties` file
* Inside this file add a `geocodeApiKey` property
* Go to [Geocoding Maps](https://geocode.maps.co/) and generate a new API key
* Assign this API key to the `geocodeApiKey` property in your `secrets.properties` file
* After this you should be able to run the app

## Functionality

Enter in an address and submit to retrieve the weather data currently at the location

* Temperature
* Apparent Temperature
* Wind including gusts and direction
* Rain
* Cloud cover

## Additional Commands

In the terminal you can run the following commands

```shell
./gradlew test
```

Runs all the unit tests in the project