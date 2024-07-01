package com.example.final_tp3.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_tp3.adapters.FoodTypeAdapter
import com.example.final_tp3.adapters.RestaurantAdapter
import com.example.final_tp3.data.Restaurant
import com.example.final_tp3.databinding.FragmentFragHomeBinding
import com.example.final_tp3.listeners.OnClickItemListener
import com.example.final_tp3.viewmodels.RestaurantViewModel

class FragHome : Fragment(), OnClickItemListener {

    private lateinit var binding: FragmentFragHomeBinding
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var foodTypeAdapter: FoodTypeAdapter
    private lateinit var restaurantViewModel: RestaurantViewModel
    private var filteredRestaurants = mutableListOf<Restaurant>()
    private var selectedFoodType : String = "All"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)

        restaurantAdapter = RestaurantAdapter(filteredRestaurants,this, restaurantViewModel)
        binding.RestaurantListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.RestaurantListRecyclerView.adapter = restaurantAdapter

        foodTypeAdapter = FoodTypeAdapter(listOf("All", "burger", "sushi", "taco", "pizza")) {
            selectedFoodType = it
            filterRestaurants()
        }
        binding.FoodTypesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.FoodTypesRecyclerView.adapter = foodTypeAdapter

        setupSearchView()

        // Initialize ViewModel and observe data

        restaurantViewModel.restaurants.observe(viewLifecycleOwner) { restaurants ->
            restaurants?.let {
                filteredRestaurants.clear()
                filteredRestaurants.addAll(it)
                filterRestaurants()
            }
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterRestaurants()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterRestaurants()
                return true
            }
        })
    }

    private fun filterRestaurants() {
        val query = binding.searchView.query.toString().lowercase()

        filteredRestaurants.clear()
        filteredRestaurants.addAll(restaurantViewModel.restaurants.value.orEmpty().filter {
            (selectedFoodType == "All" || it.Type.equals(selectedFoodType, ignoreCase = true)) &&
                    it.Name.lowercase().contains(query)
        })
        restaurantAdapter.notifyDataSetChanged()
    }

    override fun navigateToTripDetails(restaurant: Restaurant) {
        findNavController().navigate(FragHomeDirections.actionFragHomeToFragOrder(restaurant))
    }
}