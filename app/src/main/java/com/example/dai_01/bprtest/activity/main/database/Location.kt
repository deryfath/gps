package com.example.dai_01.bprtest.activity.main.database

import com.google.gson.internal.LinkedTreeMap
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Location(
        @PrimaryKey open var ID: Int = 0,
        open var latitude: String = "",
        open var longitude: String = "",
        open var accuracy: String = "",
        open var time: Date,
        open var src: String
)
    : RealmObject() {

    constructor() : this(0, "",
            "", "",
            Date(), ""
    )

    fun copy(
            ID: Int = this.ID,
            latitude: String = this.latitude,
            longitude: String = this.longitude,
            accuracy: String = this.accuracy,
            src: String = this.src,
            time: Date = this.time) = Location(ID, latitude, longitude, accuracy,time,src)
}