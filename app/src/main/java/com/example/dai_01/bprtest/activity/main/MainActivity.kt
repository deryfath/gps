package com.example.dai_01.bprtest.activity.main

import android.content.Context
import android.content.Intent
import android.location.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import com.example.dai_01.bprtest.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.LinearInterpolator
import android.widget.Toast
import com.example.dai_01.bprtest.activity.main.database.LocationModel
import com.example.dai_01.bprtest.extension.debug
import io.realm.Realm
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity(){

    private val handler = Handler()
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var locationModel = LocationModel()
    var realm = Realm.getDefaultInstance()
    var id = 1000000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initAnimation()
    }

    private fun initAnimation(){

        val rotate = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 700
        rotate.repeatCount = -1
        rotate.setInterpolator(LinearInterpolator())
        iv_loading.startAnimation(rotate)

        handler.postDelayed(Runnable {

            initContent()

        }, 500)
    }

    private fun initContent(){

        //get current location
        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {

                val lat:Double = Math.round(location.latitude * 1000.0) / 1000.0
                lat_txt.text = lat.toString()
                val long:Double = Math.round(location.longitude * 1000.0) / 1000.0
                long_txt.text = long.toString()
                acc_txt.text = location.accuracy.toString()
                val date = Date()
                time_txt.text = formatter.format(date)

                saveLocDb(id,lat.toString(),long.toString(),location.accuracy.toString(),date,"Scheduler")
                id++

                ll_main.visibility = View.VISIBLE
                rl_progresssBar.visibility = View.GONE

                get_btn.setOnClickListener {
                    debug("CLICK ADD")
                    Toast.makeText(this@MainActivity, "Location Added", Toast.LENGTH_SHORT).show()
                    saveLocDb(id,lat.toString(),long.toString(),location.accuracy.toString(),date,"Button")
                    id++
                }

            }
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager?;
        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
        } catch(ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available");
        }

        show_btn.setOnClickListener {
            val intent = Intent(it.context, ShowGPSActivity::class.java)
            startActivity(intent)
        }


    }

    private fun saveLocDb(id:Int,lat:String,long:String,acc:String,date:Date,src:String){
        val loc = com.example.dai_01.bprtest.activity.main.database.Location(
                ID = id,
                latitude = lat,
                longitude = long,
                accuracy = acc,
                time = date,
                src = src
        )

        locationModel.addLocation(realm,loc)
    }

    override fun onBackPressed() {

        finish()
        onDestroy()
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()

        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
