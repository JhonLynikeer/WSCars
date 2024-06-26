package com.jltech.wscars.api.service


import com.jltech.wscars.api.model.response.cars.CarsResponse
import retrofit2.Call
import retrofit2.http.GET

interface WSCarsService {

    @GET("cars.json")
    fun getCars(): Call<CarsResponse>

}
