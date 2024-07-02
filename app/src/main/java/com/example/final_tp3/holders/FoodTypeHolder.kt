package com.example.final_tp3.holders

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.final_tp3.R

class FoodTypeHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View

    init {
        view = v
    }

    fun setText(text: String) {
        val textTxt = view.findViewById<TextView>(R.id.food_type_text)
        textTxt.text = text
    }

    fun clickType() = view.findViewById<CardView>(R.id.food_type_card)
}