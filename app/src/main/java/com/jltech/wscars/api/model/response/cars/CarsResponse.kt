package com.jltech.wscars.api.model.response.cars


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarsResponse(
    @Json(name = "cars")
    val cars: List<Car?>?
)