package com.example.final_tp3.ui.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_tp3.R
import com.example.final_tp3.adapters.MenuAdapter
import com.example.final_tp3.data.Menu
import com.example.final_tp3.data.Restaurant
import com.example.final_tp3.databinding.FragmentFragOrderBinding
import com.example.final_tp3.ui.viewmodels.OrderViewModel

class FragOrder : Fragment() {

    private var _binding: FragmentFragOrderBinding? = null
    private val binding get() = _binding!!
    private lateinit var root: View

    private lateinit var menuAdapter : MenuAdapter
    private lateinit var menuViewModel: OrderViewModel
    private var filteredMenu = mutableListOf<Menu>()
    private lateinit var restaurant: Restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFragOrderBinding.inflate(inflater, container, false)
        root = binding.root
        // Initialize ViewModel and observe data
        menuViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        arguments?.let {
            val tripArg = FragOrderArgs.fromBundle(it).restaurant
            restaurant = tripArg

            preparateFragment(binding)

            //searchMenus(tripArg, binding)

        }

        menuAdapter = MenuAdapter(filteredMenu, menuViewModel)
        binding.recyclerOrder.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerOrder.adapter = menuAdapter






        menuViewModel._restaurant.value = restaurant
        menuViewModel.menus.observe(viewLifecycleOwner) { menus ->
            menus?.let {
                filteredMenu.clear()
                filteredMenu.addAll(it)
                menuAdapter.notifyDataSetChanged()
            }
        }
        binding.layOrderBtnBack.setOnClickListener {
            findNavController().navigate(FragOrderDirections.actionNavFragOrderToNavFragHome())
        }

        menuViewModel._restaurant.observe(viewLifecycleOwner, Observer { restaurant ->
            this.restaurant = restaurant
            restaurant?.let {
                updateSaveButtonIcon(it)
            }
        })


        return root
    }

    private fun updateSaveButtonIcon(restaurant: Restaurant) {
        binding.layOrderButtonLike.setImageResource(
            if(restaurant.saved) R.drawable.ic_heart_fill else R.drawable.ic_heart_empty)
    }

    private fun preparateFragment(binding: FragmentFragOrderBinding) {
        binding.layOrderTitleRestaurant.text = restaurant.Name
        binding.layOrderDelayRestaurant.text = restaurant.delay
        binding.layOrderUbiRestaurant.text = restaurant.ubication
        binding.layOrderButtonLike.setOnClickListener {
            menuViewModel.saveRestaurant()
        }
        //binding.layOrderButtonLike.setImageResource(if(restaurant.saved) R.drawable.ic_heart_fill else R.drawable.ic_heart_empty)
        binding.recyclerOrder.setHasFixedSize(true)
        binding.recyclerOrder.layoutManager = LinearLayoutManager(context)
    }

}