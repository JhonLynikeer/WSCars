package com.jltech.wscars.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Order(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "client")
    val client: Client,
    @Json(name = "createdAt")
    val createdAt: String? = null
)