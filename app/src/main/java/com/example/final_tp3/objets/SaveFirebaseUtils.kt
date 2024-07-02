package com.example.final_tp3.objets

import android.util.Log
import com.example.final_tp3.data.Saveable
import com.google.firebase.firestore.FirebaseFirestore

object SaveFirebaseUtils {
    fun updateRestaurant(restaurant: Saveable) {
        val db = FirebaseFirestore.getInstance()

        val restaurantID = restaurant.id
        val updates = hashMapOf<String, Any>(
            "saved" to restaurant.saved,
        )

        db.collection(Config.RESTAURANTS_COLECCTION).document(restaurantID)
            .update(updates)
            .addOnSuccessListener {

            }
            .addOnFailureListener { e ->

            }
    }
    fun updateMenu(menu: Saveable) {
        val db = FirebaseFirestore.getInstance()

        val restaurantID = menu.id
        val updates = hashMapOf<String, Any>(
            "saved" to menu.saved,
        )

        db.collection(Config.MENUS_COLECCTION).document(restaurantID)
            .update(updates)
            .addOnSuccessListener {

            }
            .addOnFailureListener { e ->

            }
    }

}
