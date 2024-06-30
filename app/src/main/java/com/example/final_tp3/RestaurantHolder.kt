package com.example.final_tp3

import android.provider.Telephony.Mms.Rate
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RestaurantHolder(v: View): RecyclerView.ViewHolder(v){
    private var view: View

    init {
        view = v
    }

    fun setName(name: String){
        val nameTxt = view.findViewById<TextView>(R.id.layItemRestaurantName)
        nameTxt.text = name
    }

    fun setRate(rate: Double){
        val rateTxt = view.findViewById<TextView>(R.id.layItemRestaurantRate)
        rateTxt.text = rate.toString()
    }

    fun setImg(image: String){
        val imgView = view.findViewById<ImageView>(R.id.itemImgPic)
        Glide.with(view.context).load(image).centerCrop().into(imgView)
    }
}