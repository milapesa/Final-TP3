package com.example.final_tp3.holders

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_tp3.R

class MenuHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View

    init {
        view = v
    }

    fun setName(name: String) {
        val nameTxt = view.findViewById<TextView>(R.id.layItemMenuName)
        nameTxt.text = name
    }

    fun setPrice(price: Double) {
        val priceString = "$$price"
        val priceTxt = view.findViewById<TextView>(R.id.layItemMenuPrice)
        priceTxt.text = priceString
    }

    fun setImg(image: String) {
        val imgView = view.findViewById<ImageView>(R.id.itemImgPic)
        Glide.with(view.context).load(image).centerCrop().into(imgView)
    }

    fun saveMenu() = view.findViewById<ImageButton>(R.id.layItemMenuLike)
}