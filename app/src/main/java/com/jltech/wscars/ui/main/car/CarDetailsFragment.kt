package com.jltech.wscars.ui.main.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.jltech.wscars.R
import com.jltech.wscars.api.model.response.cars.Car
import com.jltech.wscars.databinding.FragmentCarDetailsBinding
import com.jltech.wscars.ui.main.car.dialog.SolicitationDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.Locale


class CarDetailsFragment : Fragment() {


    private var _binding: FragmentCarDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CarsViewModel by viewModel()
    private val args: CarDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
        onClick()

    }

    private fun setupObserver() {
        viewModel.successOrder.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(requireContext(), "Logo alguem entra em contato", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Algo deu errado.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupUI() {
        image()
        val carJson = args.carDetail
        val car = Gson().fromJson(carJson, Car::class.java)
        car?.let {
            with(binding) {
                val valorNumber = car.valor?.times(1000)
                val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                tvTitleName.text = it.nomeModelo
                tvTitleAge.text = buildString {
                    append("Ano: ")
                    append(it.ano)
                }
                tvTitleDoor.text = buildString {
                    append("Portas: ")
                    append(it.numPortas)
                }
                tvColorCar.text = buildString {
                    append("Cor: ")
                    append(it.cor)
                }
                tvFuelCar.text = buildString {
                    append("Combustivel: ")
                    append(it.combustivel)
                }
                tvPriceCar.text = buildString {
                    append(format.format(valorNumber))
                }
            }
        }
    }


    private fun image() {
        val viewPager = binding.viewPager
        val imageList = mutableListOf<Int>()

        imageList.add(R.drawable.car_blue)
        imageList.add(R.drawable.car_red)
        imageList.add(R.drawable.car_blue_light)

        val adapter = ImageViewPagerAdapter(imageList) { position ->
        }

        viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
        }.attach()

    }

    private fun onClick() {

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnContact.setOnClickListener {
                showDialog()
            }
        }

    }


    private fun showDialog() {
        val carDetail = args.carDetail
        val customDialog = SolicitationDialogFragment(carDetail, {})
        customDialog.show(requireActivity().supportFragmentManager, "custom_dialog")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}