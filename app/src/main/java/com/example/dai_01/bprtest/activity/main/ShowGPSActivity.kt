package com.example.dai_01.bprtest.activity.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.example.dai_01.bprtest.R
import com.example.dai_01.bprtest.activity.main.adapter.RecyclerLocationListAdapter
import com.example.dai_01.bprtest.activity.main.database.Location
import com.example.dai_01.bprtest.activity.main.database.LocationModel
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_show_gps.*

class ShowGPSActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var locationAdapter:RecyclerLocationListAdapter
    var locationModel = LocationModel()
    var realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_gps)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.title = ""

        var result = locationModel.getLocation(realm)
        initiateRecyclerLocationList(result)

    }

    private fun initiateRecyclerLocationList(data:List<Location>) {
        rv_location.isNestedScrollingEnabled=false
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rv_location.layoutManager=linearLayoutManager
        locationAdapter= RecyclerLocationListAdapter(data.take(10).sortedWith(compareByDescending<Location> { it.time }
                .thenByDescending { it.time }))
        rv_location.adapter=locationAdapter


    }

    override fun onBackPressed() {

        finish()
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
