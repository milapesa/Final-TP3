package com.example.final_tp3.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_tp3.objets.Config
import com.example.final_tp3.data.Menu
import com.example.final_tp3.data.Restaurant
import com.example.final_tp3.data.Saveable
import com.example.final_tp3.listeners.OnClickSaveItem
import com.example.final_tp3.objets.SaveFirebaseUtils
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class OrderViewModel() : ViewModel(), OnClickSaveItem {



    private val _menus = MutableLiveData<List<Menu>?>()
    val _restaurant = MutableLiveData<Restaurant>()
    val menus: MutableLiveData<List<Menu>?> get() = _menus

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
                    val menu = document.toObject(Menu::class.java).apply {
                        id = document.id
                    }
                    if (menu.idRestaurant == _restaurant.value?.id) {
                        menusList.add(menu)
                    }
                }
                _menus.value = menusList
            }
            .addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents: ", exception)
            }
    }

    override fun saveItem(menu: Saveable) {
        if (menu.saved == true){
            menu.saved = false

        } else {
            menu.saved = true

        }


        val currentList = menus.value?.toMutableList()

        val index = currentList?.indexOf(menu)


        if (index != null && index != -1) {

            currentList[index] = menu as Menu
            menus.postValue(currentList)
        }
        SaveFirebaseUtils.updateMenu(menu)
    }

    fun saveRestaurant() {
        viewModelScope.launch {
            if (_restaurant.value?.saved == true){
                _restaurant.value?.saved = false
                _restaurant.postValue(_restaurant.value)

            } else {
                _restaurant.value?.saved = true
                _restaurant.postValue(_restaurant.value)

            }
        }
        SaveFirebaseUtils.updateRestaurant(_restaurant.value!!)

    }


}