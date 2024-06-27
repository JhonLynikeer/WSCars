package com.jltech.wscars.ui.account.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jltech.wscars.databinding.FragmentLoginBinding
import com.jltech.wscars.databinding.FragmentStepOnboardingBinding
import com.jltech.wscars.ui.main.MainActivity
import com.jltech.wscars.utils.isValidEmail


class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()

    }

    private fun validation() {
//        val email = "cliente@teste.com"
//        val password = "123456789"
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        var valideFields = true

        if (!email.isValidEmail()) {
            binding.editEmail.error = "Email inválido"
            valideFields = false
        }

        if (password.isEmpty()) {
            binding.editPassword.error = "Campo vazio"
            valideFields = false
        }

        if (valideFields) {
            Toast.makeText(requireContext(), "Crendenciais incorretas", Toast.LENGTH_SHORT).show()
        }


    }


    private fun onClick() {

        binding.apply {
            btnContinue.setOnClickListener {
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            }
            btnEnter.setOnClickListener {
                validation()
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}