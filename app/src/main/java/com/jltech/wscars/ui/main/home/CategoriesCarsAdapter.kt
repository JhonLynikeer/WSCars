package com.jltech.wscars.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jltech.wscars.api.model.response.cars.Car
import com.jltech.wscars.api.model.response.cars.CarsResponse
import com.jltech.wscars.api.model.response.categories.CategoriesResponse
import com.jltech.wscars.databinding.ItemHomeCategoryBinding
import com.jltech.wscars.databinding.ItemHomeRecommendedBinding
import java.text.NumberFormat
import java.util.Locale


class CategoriesCarsAdapter(
    private var data: List<CategoriesResponse>,
    private val onClickListener: (CategoriesResponse) -> Unit
) : RecyclerView.Adapter<CategoriesCarsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemHomeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoriesResponse) {

            with(binding){
                imageCategory.load(item.image)
                tvNameCategory.text = buildString {
                    append(item.name)
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
    ): CategoriesCarsAdapter.ViewHolder {
        val binding =
            ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesCarsAdapter.ViewHolder, position: Int) {
        data[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data.size
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemViewType(position: Int): Int = position

    fun updateData(newData: List<CategoriesResponse>) {
        data = newData
        notifyDataSetChanged()
    }

}