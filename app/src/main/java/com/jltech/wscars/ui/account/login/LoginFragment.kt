package com.jltech.wscars.ui.account.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jltech.wscars.databinding.FragmentLoginBinding
import com.jltech.wscars.databinding.FragmentStepOnboardingBinding
import com.jltech.wscars.ui.main.MainActivity


class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
   // private val viewModel by viewModel<AdvertisementViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
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
            btnContinue.setOnClickListener {
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


}