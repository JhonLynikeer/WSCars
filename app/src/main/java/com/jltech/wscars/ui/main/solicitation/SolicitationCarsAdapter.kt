package com.jltech.wscars.ui.main.solicitation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jltech.wscars.api.model.response.cars.Car
import com.jltech.wscars.api.model.response.cars.CarsResponse
import com.jltech.wscars.data.model.Order
import com.jltech.wscars.databinding.ItemHomeRecommendedBinding
import java.text.NumberFormat
import java.util.Locale
import kotlin.time.times


class SolicitationCarsAdapter(
    private var data: List<Order>,
    private val onClickListener: (Order) -> Unit
) : RecyclerView.Adapter<SolicitationCarsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemHomeRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Order) {

            with(binding){
                tvModelCar.text = buildString {
                    append("Modelo: ")
                    append(item.client.car.nameModel)
                }

                tvAgeCar.text = buildString {
                    append("Ano: ")
                    append(item.client.car.ano)
                }

                val valorNumber = item.client.car.price
                val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

                binding.tvPriceCar.text = buildString {
                    append(format.format(valorNumber))
                }
            }

            binding.root.setOnClickListener {
                onClickListener(item)
            }


        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SolicitationCarsAdapter.ViewHolder {
        val binding =
            ItemHomeRecommendedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SolicitationCarsAdapter.ViewHolder, position: Int) {
        data[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data.size
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemViewType(position: Int): Int = position

    fun updateData(newData: List<Order>) {
        data = newData
        notifyDataSetChanged()
    }

}