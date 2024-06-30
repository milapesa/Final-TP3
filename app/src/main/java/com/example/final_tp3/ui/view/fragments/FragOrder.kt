package com.example.final_tp3.ui.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.final_tp3.R

class FragOrder : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let {
            val safeArgs = FragOrderArgs.fromBundle(it)
            Log.e("FragOrder", safeArgs.Restaurant.menu.toString())
        }
        return inflater.inflate(R.layout.fragment_frag_order, container, false)
    }
}