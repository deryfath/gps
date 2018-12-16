package com.example.dai_01.bprtest

import android.app.Application
import com.example.dai_01.bprtest.dagger.component.AppComponent
import com.example.dai_01.bprtest.dagger.component.DaggerAppComponent
import com.example.dai_01.bprtest.dagger.module.ApiModule
import com.example.dai_01.bprtest.dagger.module.AppModule
import com.example.dai_01.bprtest.dagger.module.NetworkModule
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .networkModule(NetworkModule("https://api.apixu.com"))
                    .apiModule(ApiModule())
                    .build()

        Realm.init(getApplicationContext());
        var c = RealmConfiguration.Builder()
        c.name("location")
        c.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(c.build())
    }
}