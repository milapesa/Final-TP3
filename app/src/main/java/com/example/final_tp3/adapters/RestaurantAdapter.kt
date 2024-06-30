package com.example.final_tp3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.final_tp3.R
import com.example.final_tp3.RestaurantHolder
import com.example.final_tp3.data.Restaurant
import com.example.final_tp3.databinding.LayItemRestaurantBinding

class RestaurantAdapter(
    private val restaurants: MutableList<Restaurant>
) : RecyclerView.Adapter<RestaurantHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lay_item_restaurant,parent,false)
        return(RestaurantHolder(view))
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.setName(restaurant.Name)
        holder.setRate(restaurant.Rate)
        holder.setImg(restaurant.Img)
    }


}
