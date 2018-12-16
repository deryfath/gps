package com.example.dai_01.bprtest.extension

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.*
import android.provider.SyncStateContract
import android.util.Log
import com.example.dai_01.bprtest.R
import java.io.IOException
import java.util.*
import android.os.Bundle
import com.example.dai_01.bprtest.model.Forecastday


fun getDayOfWeek(day : Int):String{

    var dayString =""
    when (day){
        1 -> dayString = "Sunday"
        2 -> dayString = "Monday"
        3 -> dayString = "Tuesday"
        4 -> dayString = "Wednesday"
        5 -> dayString = "Thursday"
        6 -> dayString = "Friday"
        7 -> dayString = "Saturday"
    }

    return dayString
}


fun getLocation(context: Context):MutableList<Address>{

    var arrAddress: MutableList<Address> = arrayListOf()

    var lat : Double = 0.0
    var long : Double = 0.0

    val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            lat = location.longitude
            long = location.latitude

        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    val locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager?;
    try {
        // Request location updates
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
    } catch(ex: SecurityException) {
        Log.d("myTag", "Security Exception, no location available");
    }

    val geocoder = Geocoder(context, Locale.getDefault())
    arrAddress = geocoder.getFromLocation(lat, long, 1)

    debug("LAT : $lat & long : $long")

    return arrAddress
}

