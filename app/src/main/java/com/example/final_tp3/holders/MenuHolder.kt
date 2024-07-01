package com.example.final_tp3.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
}