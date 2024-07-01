package com.example.final_tp3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.final_tp3.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.initialize

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Firebase.initialize(this) // This line


        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener {
            recreate()
        }

        bottomNavigation = findViewById(R.id.bottom_bar)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment

        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(bottomNavigation, navHostFragment.navController)

    }
}