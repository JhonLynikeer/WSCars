package com.jltech.wscars.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Car(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "idModel")
    val idModel: Int,
    @Json(name = "ano")
    val ano: Int,
    @Json(name = "fuelType")
    val fuelType: String,
    @Json(name = "numDoor")
    val numDoor: Int,
    @Json(name = "color")
    val color: String,
    @Json(name = "nameModel")
    val nameModel: String,
    @Json(name = "price")
    val price: Double
)