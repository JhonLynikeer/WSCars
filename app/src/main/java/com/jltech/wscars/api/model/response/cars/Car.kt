package com.jltech.wscars.api.model.response.cars


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Car(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "timestamp_cadastro")
    val timestampCadastro: Long?,
    @Json(name = "modelo_id")
    val modeloId: Int?,
    @Json(name = "ano")
    val ano: Int?,
    @Json(name = "combustivel")
    val combustivel: String?,
    @Json(name = "num_portas")
    val numPortas: Int?,
    @Json(name = "cor")
    val cor: String?,
    @Json(name = "nome_modelo")
    val nomeModelo: String?,
    @Json(name = "valor")
    val valor: Double?
)