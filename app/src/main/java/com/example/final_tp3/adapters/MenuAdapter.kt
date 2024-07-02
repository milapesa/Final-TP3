package com.example.final_tp3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.final_tp3.R
import com.example.final_tp3.data.Menu
import com.example.final_tp3.holders.MenuHolder
import com.example.final_tp3.listeners.OnClickSaveItem

class MenuAdapter(
    private val menus: MutableList<Menu>,
    private val saveListener : OnClickSaveItem
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
        holder.saveMenu().setOnClickListener {
            saveListener.saveItem(menu)
        }
        holder.saveMenu().setImageResource(if(menu.saved) R.drawable.ic_saved_menu else R.drawable.ic_not_saved_menu)

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