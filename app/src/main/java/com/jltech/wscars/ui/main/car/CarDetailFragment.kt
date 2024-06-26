package com.jltech.wscars.ui.main.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jltech.wscars.api.model.response.cars.Car
import com.jltech.wscars.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class CarDetailFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
     private val viewModel: CarsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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