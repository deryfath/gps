package com.example.dai_01.bprtest.activity.main.database

import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort

class LocationModel : LocationView {

    override fun addLocation(realm: Realm, location: Location): Boolean {
        try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(location)
            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    override fun getLocation(realm: Realm): RealmResults<Location> {
        return realm.where(Location::class.java).findAll()
    }
}