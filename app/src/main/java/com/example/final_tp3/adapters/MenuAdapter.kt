package com.example.final_tp3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.final_tp3.R
import com.example.final_tp3.data.Menu
import com.example.final_tp3.holders.MenuHolder

class MenuAdapter(
    private val menus: MutableList<Menu>
): RecyclerView.Adapter<MenuHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lay_item_menu,parent,false)
        return(MenuHolder(view))
    }

    override fun getItemCount() = menus.size

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        val menu = menus[position]
        holder.setName(menu.item)
        holder.setPrice(menu.price)
        holder.setImg(menu.img)
    }
}