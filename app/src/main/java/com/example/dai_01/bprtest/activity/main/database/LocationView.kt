package com.example.dai_01.bprtest.activity.main.database

import io.realm.Realm
import io.realm.RealmResults

interface LocationView {
    fun addLocation(realm: Realm, location: Location): Boolean
    fun getLocation(realm: Realm): RealmResults<Location>
}