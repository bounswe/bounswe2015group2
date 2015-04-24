## **Location Based Applications** ##
Location finding is one of the most important used in mobile application recently. Since mobile users keep their mobile phones 7x24 with themselves, it’s a great opportunity to provide the users with different features based on the location. Thanks to location awareness, users can get more contextual experience.
## **The most commonly known location based applications** ##

### **Foursquare** ###
It’s a location-aware smart search application focusing on discovery.


![http://media.bestofmicro.com/9/O/457836/original/Foursquare.jpg](http://media.bestofmicro.com/9/O/457836/original/Foursquare.jpg)

### **Swarm** ###
It’s an application providing its users with meeting up with their friends by broadcasting their location to their contacts and enabling them to see who is nearby.


![http://media.bestofmicro.com/S/8/442952/original/Swarm.jpg](http://media.bestofmicro.com/S/8/442952/original/Swarm.jpg)

### **Yahoo! Weather** ###
It’s an application provided its users with detailed 5-day forecasts based on their location.


![http://media.bestofmicro.com/9/T/457841/original/YahooWeather.jpg](http://media.bestofmicro.com/9/T/457841/original/YahooWeather.jpg)

### **Life 360** ###
It’s another application using location based services such that its users know where their family or group members are on a map. Besides it has the following features: in-app messaging, panic button. Panic button is a very important feature provided only with location based services. Via GPS location detected the button immediately sends a phone call, email and text message to users family members or friends.


![http://media.bestofmicro.com/V/3/445647/original/Life360.jpg](http://media.bestofmicro.com/V/3/445647/original/Life360.jpg)


## **Making Your App Location-Aware** ##
The location APIs available both in Google Play services and Android framework. But, it’s highly recommended to use Google Play services location APIs. It facilitates adding location awareness to your app with automated location tracking, geofencing, and activity recognition.
As an Android Application Developer,
  * You may want to find the last known location of the user because generally it’s the same as the user’s current location. For example, a panic button application may take advantage of the user’s last known location to find the user. In order to find out how to get the last location with Google Play location services, check the methods [here](https://developer.android.com/training/location/retrieve-current.html)
  * You may want to track the user continuously. For example, while counting the number of steps of users, or navigating a driver to find a place, you should track the location of the user continuously. In order to find out how to receive location updates with Google Play location services, check the methods [here](https://developer.android.com/training/location/receive-location-updates.html)
  * You may want to find the location address instead of latitude and longitude information you find via other methods, it’s called reverse geocoding. In order to find out how to convert latitude and longitude from the location object to the location address, check the methods [here](https://developer.android.com/training/location/display-address.html)


References:

  * http://www.tomsguide.com/us/best-location-aware-apps,review-2405.html
  * https://developer.android.com/training/location/index.html
  * http://www.tutorialspoint.com/android/android_location_based_services.htm