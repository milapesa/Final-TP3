package com.example.final_tp3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.final_tp3.R
import com.example.final_tp3.holders.RestaurantHolder
import com.example.final_tp3.data.Restaurant
import com.example.final_tp3.listeners.OnClickItemListener
import com.example.final_tp3.listeners.OnClickSaveItem

class RestaurantAdapter(
    private val restaurants: MutableList<Restaurant>,
    private val clickListener : OnClickItemListener,
    private val saveListener : OnClickSaveItem
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
        holder.navigateToRestaurantDetails().setOnClickListener{
            clickListener.navigateToTripDetails(restaurant)
        }

        holder.saveRestaurant().setOnClickListener{
            saveListener.saveItem(restaurant)
        }
        holder.saveRestaurant().setImageResource(if(restaurant.saved) R.drawable.ic_heart_fill else R.drawable.ic_heart_empty)

        // deja un espacio al ultimo item del recycler view para que no se solape con el floating action button
        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
        if (position == itemCount - 1) {
            layoutParams.bottomMargin = (50 * holder.itemView.context.resources.displayMetrics.density).toInt()
        } else {
            layoutParams.bottomMargin = 0
        }
        holder.itemView.layoutParams = layoutParams

    }


}
