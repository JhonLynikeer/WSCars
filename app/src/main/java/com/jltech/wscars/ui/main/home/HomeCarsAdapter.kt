package com.jltech.wscars.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jltech.wscars.api.model.response.cars.Car
import com.jltech.wscars.api.model.response.cars.CarsResponse
import com.jltech.wscars.databinding.ItemHomeRecommendedBinding
import java.text.NumberFormat
import java.util.Locale


class HomeCarsAdapter(
    private var data: List<Car?>,
    private val onClickListener: (Car?) -> Unit
) : RecyclerView.Adapter<HomeCarsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemHomeRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Car) {

            with(binding){
                tvModelCar.text = buildString {
                    append("Modelo: ")
                    append(item.nomeModelo)
                }

                tvAgeCar.text = buildString {
                    append("Ano: ")
                    append(item.ano)
                }

                val valorNumber = item.valor?.times(1000)
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
    ): HomeCarsAdapter.ViewHolder {
        val binding =
            ItemHomeRecommendedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeCarsAdapter.ViewHolder, position: Int) {
        data[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data.size
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemViewType(position: Int): Int = position

    fun updateData(newData: List<Car?>) {
        data = newData
        notifyDataSetChanged()
    }

}