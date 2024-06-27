package com.jltech.wscars.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Client(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "nome")
    val nome: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "birthday")
    val birthday: String,
    @Json(name = "car")
    val car: Car
)