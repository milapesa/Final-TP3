package com.example.final_tp3.listeners

import com.example.final_tp3.data.Restaurant

interface OnClickItemListener {
    fun navigateToTripDetails(restaurant: Restaurant)
}