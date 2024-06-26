package com.jltech.wscars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jltech.wscars.databinding.FragmentStepOnboardingBinding


class BaseFragment : Fragment() {


    private var _binding: FragmentStepOnboardingBinding? = null
    private val binding get() = _binding!!
   // private val viewModel by viewModel<AdvertisementViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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