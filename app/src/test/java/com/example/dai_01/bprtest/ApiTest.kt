package com.example.dai_01.bprtest

import com.example.dai_01.bprtest.service.ApiService
import org.junit.Test
import org.mockito.Mockito

class ApiTest {

    @Test
    fun testWeatherApiRequest(){
        val apiRepository = Mockito.mock(ApiService::class.java)
        apiRepository.getWeatherList("Bandung")
        Mockito.verify(apiRepository).getWeatherList("Bandung")
    }

}