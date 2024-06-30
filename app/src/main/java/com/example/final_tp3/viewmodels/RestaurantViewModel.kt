package com.example.final_tp3.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.final_tp3.data.Restaurant
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class RestaurantViewModel : ViewModel() {

    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> get() = _restaurants

    init {
        fetchRestaurants()
    }

    private fun fetchRestaurants() {

        val db = Firebase.firestore
        val restaurantList = mutableListOf<Restaurant>()

        db.collection("Restaurants")
            .get()
            .addOnSuccessListener { task ->
                for (document in task) {
                    val restaurant = document.toObject(Restaurant::class.java)
                    restaurantList.add(restaurant)
                    Log.d("firebase", "Fetched restaurant: ${restaurantList[0]}")
                }
                _restaurants.value = restaurantList
            }
            .addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents.", exception)
            }
    }
}