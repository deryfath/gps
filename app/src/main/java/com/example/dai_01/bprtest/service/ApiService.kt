package com.example.dai_01.bprtest.service

import com.example.dai_01.bprtest.model.WeatherListResponse
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/forecast.json?key=662fb13edae847f3a5c101238182911&days=5")
    fun getWeatherList(@Query("q") q :String) : Observable<WeatherListResponse>
}