package com.example.final_tp3.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.final_tp3.config.Config
import com.example.final_tp3.data.Menu
import com.example.final_tp3.data.Restaurant
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class OrderViewModel() : ViewModel() {



    private val _menus = MutableLiveData<List<Menu>>()
    val menus: LiveData<List<Menu>> get() = _menus
    val idRestaurant = MutableLiveData<String>()

    init {
        fetchMenus()
    }

    private fun fetchMenus() {
        val db = Firebase.firestore
        val menusList = mutableListOf<Menu>()

        db.collection(Config.MENUS_COLECCTION)
            //.whereEqualTo("idRestaurant", idRestaurant.value)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val menu = document.toObject(Menu::class.java)
                    if (menu.idRestaurant == idRestaurant.value){
                        menusList.add(menu)
                    }

                }
                _menus.value = menusList
            }
            .addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents: ", exception)
            }
    }




}