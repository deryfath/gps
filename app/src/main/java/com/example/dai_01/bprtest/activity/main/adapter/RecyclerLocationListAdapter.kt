package com.example.dai_01.bprtest.activity.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dai_01.bprtest.R
import com.example.dai_01.bprtest.activity.main.database.Location
import com.example.dai_01.bprtest.extension.debug
import com.example.dai_01.bprtest.extension.getDayOfWeek
import com.example.dai_01.bprtest.extension.inflate
import com.example.dai_01.bprtest.model.Forecastday
import java.text.SimpleDateFormat
import java.util.*

class RecyclerLocationListAdapter (private val items:List<Location>) : RecyclerView.Adapter<RecyclerLocationListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflatedView= parent!!.inflate(R.layout.location_list_item,false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val view=holder?.itemView
        val data=items[position]
        view?.let {
            it.visibility= View.VISIBLE

            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

            (it.findViewById(R.id.date_item_txt) as TextView).text = formatter.format(data.time)
            (it.findViewById(R.id.lat_item_txt) as TextView).text = "Latitude : "+data.latitude
            (it.findViewById(R.id.long_item_txt) as TextView).text = "Longitude : "+data.longitude
            (it.findViewById(R.id.acc_item_txt) as TextView).text = "Accuracy : "+data.accuracy
            (it.findViewById(R.id.src_item_txt) as TextView).text = "Src : "+data.src


        }


    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){

    }
}