package com.jltech.wscars.ui.main.solicitation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.jltech.wscars.R
import com.jltech.wscars.api.model.response.cars.Car
import com.jltech.wscars.data.model.Client
import com.jltech.wscars.data.model.Order
import com.jltech.wscars.databinding.FragmentCarDetailsBinding
import com.jltech.wscars.databinding.FragmentHomeBinding
import com.jltech.wscars.databinding.FragmentSolicitationBinding
import com.jltech.wscars.ui.main.car.CarsViewModel
import com.jltech.wscars.ui.main.home.HomeCarsAdapter
import com.jltech.wscars.ui.main.home.HomeFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.Locale


class SolicitationFragment : Fragment() {


    private var _binding: FragmentSolicitationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SolicitationViewModel by viewModel()
    private lateinit var solicitationCarsAdapter: SolicitationCarsAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSolicitationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
        onClick()

    }

    private fun setupObserver() {

    }

    private fun setupUI() {
        val listSolicitation = viewModel.getSolicitation()
        initListCars(listSolicitation)
    }

    private fun initListCars(list: List<Order>) {
        binding.rvSolicitation.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            solicitationCarsAdapter = SolicitationCarsAdapter(emptyList()) { car ->
                val carJson = Gson().toJson(car)
             //   findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToCarDetailsFragment(carDetail = carJson))
            }

            adapter = solicitationCarsAdapter
        }

        solicitationCarsAdapter.updateData(list)

    }


    private fun onClick() {

        binding.apply {

        }

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}