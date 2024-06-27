package com.jltech.wscars.ui.main.car.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.crosoften.picplants.utils.MaskEditUtil
import com.google.gson.Gson
import com.jltech.wscars.R
import com.jltech.wscars.data.model.Car
import com.jltech.wscars.data.model.Client
import com.jltech.wscars.data.model.Order
import com.jltech.wscars.databinding.FragmentSolicitationDialogBinding
import com.jltech.wscars.ui.main.car.CarsViewModel
import com.jltech.wscars.utils.Constants
import com.jltech.wscars.utils.isValidEmail
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar


class SolicitationDialogFragment constructor(
    private val carDetails: String,
    private val actionOnDismiss: () -> Unit = {}

) : DialogFragment() {

    private var _binding: FragmentSolicitationDialogBinding? = null
    private val binding: FragmentSolicitationDialogBinding get() = _binding!!
    private val viewModel: CarsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentSolicitationDialogBinding.inflate(
        inflater, container, false
    ).apply {
        _binding = this
    }.root

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        onClick()
        setMask()
        observer()
    }

    private fun setupViews(){


    }

    private fun setMask() {

        binding.editPhone.addTextChangedListener(
            MaskEditUtil.mask(
                binding.editPhone,
                Constants.FORMAT_CELPHONE
            )
        )
        binding.editBirthday.addTextChangedListener(
            MaskEditUtil.mask(
                binding.editBirthday,
                Constants.FORMAT_DATE_FULL
            )
        )

    }



    private fun observer() {
        viewModel.successOrder.observe(viewLifecycleOwner) { sucesso ->
            if (sucesso){
                dismiss()
                actionOnDismiss.invoke()
                val item = viewModel.getSolicitation().last()
                viewModel.postLeads(item)
                Toast.makeText(requireContext(), "Um de nossos vendedores entrará em contato.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Algo deu errado", Toast.LENGTH_SHORT).show()
            }

        }

        viewModel.successPostLeads.observe(viewLifecycleOwner) { sucesso ->
            Toast.makeText(requireContext(), "deu ceerto", Toast.LENGTH_SHORT).show()
        }


    }

    private fun validate() {

        val name = binding.editName.text.toString()
        val email = binding.editEmail.text.toString()
        val phone = binding.editPhone.text.toString()
        val birthday = binding.editBirthday.text.toString()

        var isAllFieldsValid = true

        if (name.isNullOrEmpty()) {
            binding.editName.error = "Coloque o nome por favor"
            isAllFieldsValid = false
        }

        if (!email.isValidEmail()) {
            binding.editEmail.error = "Digite um e-mail válido"
            isAllFieldsValid = false
        } else {
            binding.editEmail.error = null
        }

        if (phone.isNullOrEmpty()) {
            binding.editPhone.error = "Coloque seu telefone por favor"
            isAllFieldsValid = false
        }

        if (birthday.isEmpty()) {
            binding.editBirthday.error = "Digite uma data valida"
            isAllFieldsValid = false
        } else {
            try {
                val dateFormat = SimpleDateFormat("dd/MM/yyyy")
                dateFormat.isLenient = false
                val date = dateFormat.parse(birthday)
                val calendar = Calendar.getInstance()
                calendar.time = date
            } catch (e: ParseException) {
                binding.editBirthday.error = "Digite uma data valida"
                isAllFieldsValid = false
            }
        }

        if (isAllFieldsValid) {
            val carDetail = Gson().fromJson(carDetails, com.jltech.wscars.api.model.response.cars.Car::class.java)
            val car = Car(
                id = carDetail.id,
                idModel = carDetail.modeloId!!,
                ano = carDetail.ano!!,
                fuelType = carDetail.combustivel!!,
                numDoor = carDetail.numPortas!!,
                color = carDetail.cor!!,
                nameModel = carDetail.nomeModelo!!,
                price = carDetail.valor!!.times(1000),
            )

            val cliente = Client(
                nome = name,
                email = email,
                phone = phone,
                birthday = birthday,
                car = car
            )

            val order = Order(
                client = cliente
            )

            viewModel.addOrder(order)
        }
    }

    private fun onClick(){
        binding.btnFinsh.setOnClickListener {
            validate()
        }

        binding.close.setOnClickListener {
            dismiss()
            actionOnDismiss.invoke()
        }
    }

}