package com.example.dai_01.bprtest.dagger.component

import com.example.dai_01.bprtest.activity.main.MainActivity
import com.example.dai_01.bprtest.dagger.module.ApiModule
import com.example.dai_01.bprtest.dagger.module.AppModule
import com.example.dai_01.bprtest.dagger.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        ApiModule::class
))

interface AppComponent {

    fun inject(mainActivity:MainActivity)
}