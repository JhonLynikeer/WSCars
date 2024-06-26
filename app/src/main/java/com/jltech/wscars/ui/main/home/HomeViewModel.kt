package com.jltech.wscars.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jltech.wscars.R
import com.jltech.wscars.api.model.response.cars.CarsResponse
import com.jltech.wscars.api.model.response.categories.CategoriesResponse
import com.jltech.wscars.repository.WSCarsRepository
import com.jltech.wscars.utils.ResponseParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel(
    private val repository: WSCarsRepository,
) : ViewModel() {


    val successGetCars = MutableLiveData<CarsResponse>()
    val error = MutableLiveData<String>()

    fun getCars() {
        val request = repository.getCars()
        request.enqueue(object : Callback<CarsResponse> {
            override fun onResponse(call: Call<CarsResponse>, response: Response<CarsResponse>) {
                if (response.isSuccessful) {
                    successGetCars.postValue(response.body())
                } else {
                    error.postValue(ResponseParser.parseError(response))
                }
            }

            override fun onFailure(call: Call<CarsResponse>, t: Throwable) {
                error.postValue(t.message)
            }

        })

    }

    fun listCategory(): MutableList<CategoriesResponse> {
        val list = mutableListOf<CategoriesResponse>()
        list.add(CategoriesResponse(1, R.drawable.ic_diesel, "Diesel"))
        list.add(CategoriesResponse(2, R.drawable.ic_flex, "Flex"))
        list.add(CategoriesResponse(3, R.drawable.ic_gas, "Gasolina"))
        list.add(CategoriesResponse(4, R.drawable.ic_ethanol, "Etanol"))
        list.add(CategoriesResponse(1, R.drawable.ic_diesel, "Diesel"))
        list.add(CategoriesResponse(2, R.drawable.ic_flex, "Flex"))
        list.add(CategoriesResponse(3, R.drawable.ic_gas, "Gasolina"))
        list.add(CategoriesResponse(4, R.drawable.ic_ethanol, "Etanol"))
        return list
    }

}