package com.jltech.wscars.ui.main.solicitation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jltech.wscars.R
import com.jltech.wscars.api.model.response.cars.CarsResponse
import com.jltech.wscars.api.model.response.categories.CategoriesResponse
import com.jltech.wscars.data.model.Order
import com.jltech.wscars.repository.OrderRepository
import com.jltech.wscars.repository.WSCarsRepository
import com.jltech.wscars.utils.ResponseParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SolicitationViewModel(
    private val orderRepository: OrderRepository,
) : ViewModel() {

    fun getSolicitation(): List<Order> {
        return orderRepository.getOrders()
    }

}