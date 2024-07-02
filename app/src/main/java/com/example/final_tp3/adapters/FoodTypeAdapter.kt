package com.example.final_tp3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.final_tp3.R
import com.example.final_tp3.databinding.ItemFoodTypeHomeBinding

class FoodTypeAdapter (
    private val foodTypes: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<FoodTypeAdapter.FoodTypeViewHolder>() {

    private var selectedPosition: Int = 0

    inner class FoodTypeViewHolder(private val binding: ItemFoodTypeHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foodTypeString: String) {

            binding.foodTypeText.text = foodTypeString

            binding.root.setOnClickListener {
                onClick(foodTypeString)

                val previousPosition = selectedPosition

                selectedPosition = adapterPosition

                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
            }
            binding.foodTypeImg.setImageResource(
                when (foodTypeString) {
                    "burger" -> R.drawable.ic_hamburger
                    "sushi" -> R.drawable.ic_sushi
                    "taco" -> R.drawable.ic_taco
                    "pizza" -> R.drawable.ic_pizza
                    else -> R.drawable.transparent
                }
            )
            binding.foodTypeCard.setCardBackgroundColor(
                if (adapterPosition == selectedPosition) {
                    ContextCompat.getColor(binding.root.context, R.color.colorPrimary)
                } else {
                    ContextCompat.getColor(binding.root.context, R.color.light_silver)
                }
            )
            binding.foodTypeText.setTextColor(
                if (adapterPosition == selectedPosition) {
                    ContextCompat.getColor(binding.root.context, R.color.white)
                } else {
                    ContextCompat.getColor(binding.root.context, R.color.smoky_black)
                }
            )
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodTypeViewHolder {
        val binding =
            ItemFoodTypeHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodTypeViewHolder, position: Int) {
        holder.bind(foodTypes[position])
    }

    override fun getItemCount(): Int = foodTypes.size
}