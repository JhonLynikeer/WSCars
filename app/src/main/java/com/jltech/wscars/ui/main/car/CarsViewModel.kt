package com.jltech.wscars.ui.main.car

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jltech.wscars.R
import com.jltech.wscars.api.model.response.cars.CarsResponse
import com.jltech.wscars.api.model.response.categories.CategoriesResponse
import com.jltech.wscars.data.model.Order
import com.jltech.wscars.repository.OrderRepository
import com.jltech.wscars.repository.WSCarsRepository
import com.jltech.wscars.utils.ResponseParser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CarsViewModel(
    private val orderRepository: OrderRepository,
) : ViewModel() {

    val successOrder = MutableLiveData<Boolean>()

    fun addOrder(order: Order){
        val success = orderRepository.addOrder(order)
        successOrder.postValue(success)
    }

    fun getSolicitation(): List<Order> {
        return orderRepository.getOrders()
    }

    val successPostLeads = MutableLiveData<ResponseBody>()
    val successPostListLeads = MutableLiveData<ResponseBody>()

    val error = MutableLiveData<String>()

    fun postLeads(order: Order) {
        val request = orderRepository.postLeads(order)
        request.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    successPostLeads.postValue(response.body())
                } else {
                    error.postValue(ResponseParser.parseError(response))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                error.postValue(t.message)
            }

        })

    }

    fun postLeadsList(order: List<Order>) {
        val request = orderRepository.postLeadsList(order)
        request.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    successPostListLeads.postValue(response.body())
                } else {
                    error.postValue(ResponseParser.parseError(response))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                error.postValue(t.message)
            }

        })

    }

}