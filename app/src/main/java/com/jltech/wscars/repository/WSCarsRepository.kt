package com.jltech.wscars.repository

import com.jltech.wscars.api.service.WSCarsService


class WSCarsRepository(
    private val vetService: WSCarsService
) {

    fun getCars() = vetService.getCars()

}