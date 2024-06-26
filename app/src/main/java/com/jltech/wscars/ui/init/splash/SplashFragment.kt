package com.jltech.wscars.ui.init.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jltech.wscars.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDelay()
    }

    private fun initDelay(){
        val t = Timer()
        var counter = 0
        val tt: TimerTask = object : TimerTask() {
            override fun run() {
                counter++
                if (counter == 50){
                    CoroutineScope(Dispatchers.Main).launch {
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnboardingFragment())
//                        if(applicationContext.getPreferenceData().checkLogin()){
//                            if (applicationContext.getPreferenceData().getType() == "client"){
//                                startActivity(Intent(applicationContext, MainPatientActivity::class.java))
//                            } else {
//                                startActivity(Intent(applicationContext, MainVeterinarianActivity::class.java))
//                            }
//                            finish()
//                        }else{
//                            startActivity(Intent(applicationContext, OnboardingActivity::class.java))
//                            finish()
//                        }
                    }
                    t.cancel()
                }
            }
        }
        t.schedule(tt, 0, 50)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}