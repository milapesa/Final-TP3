package com.example.final_tp3.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.final_tp3.objets.Config
import com.example.final_tp3.data.Restaurant
import com.example.final_tp3.data.Saveable
import com.example.final_tp3.listeners.OnClickSaveItem
import com.example.final_tp3.objets.SaveFirebaseUtils
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class RestaurantViewModel : ViewModel(), OnClickSaveItem {

    private val _restaurants = MutableLiveData<List<Restaurant>?>()
    val restaurants: MutableLiveData<List<Restaurant>?> get() = _restaurants

    init {
        fetchRestaurants()
    }

    private fun fetchRestaurants() {
        val db = Firebase.firestore
        val restaurantList = mutableListOf<Restaurant>()

        db.collection(Config.RESTAURANTS_COLECCTION)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val restaurant = document.toObject(Restaurant::class.java).apply {
                        id = document.id
                    }
                    restaurantList.add(restaurant)

                }
                _restaurants.value = restaurantList
            }
            .addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents.", exception)
            }
    }


    override fun saveItem(restaurant: Saveable) {
        if (restaurant.saved == true) {
            restaurant.saved = false

        } else {
            restaurant.saved = true

        }


        val currentList = restaurants.value?.toMutableList()

        val index = currentList?.indexOf(restaurant)

        if (index != null && index != -1) {

            currentList[index] = restaurant as Restaurant
            restaurants.postValue(currentList)
        }
        SaveFirebaseUtils.updateRestaurant(restaurant)
    }
}