package com.jltech.wscars.api.service


import com.jltech.wscars.api.model.response.cars.CarsResponse
import com.jltech.wscars.data.model.Order
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WSCarsService {

    @GET("cars.json")
    fun getCars(): Call<CarsResponse>

    @POST("cars/leads")
    fun postLeads(
        @Body carsRequest: Order
    ): Call<ResponseBody>

    @POST("cars/leads")
    fun postLeadsList(
        @Body carsRequest: List<Order>
    ): Call<ResponseBody>



}
