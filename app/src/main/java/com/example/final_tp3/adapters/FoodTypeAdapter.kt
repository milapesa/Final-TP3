package com.example.final_tp3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.final_tp3.databinding.ItemFoodTypeHomeBinding

class FoodTypeAdapter (
    private val foodTypes: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<FoodTypeAdapter.FoodTypeViewHolder>() {

    inner class FoodTypeViewHolder(private val binding: ItemFoodTypeHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(foodType: String) {
            binding.foodTypeText.text = foodType
            binding.root.setOnClickListener {
                onClick(foodType)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodTypeViewHolder {
        val binding = ItemFoodTypeHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodTypeViewHolder, position: Int) {
        holder.bind(foodTypes[position])
    }

    override fun getItemCount(): Int = foodTypes.size
}