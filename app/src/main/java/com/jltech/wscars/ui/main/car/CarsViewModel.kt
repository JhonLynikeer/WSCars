package com.jltech.wscars.ui.main.car

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


class CarsViewModel(
    private val repository: WSCarsRepository,
) : ViewModel() {




}