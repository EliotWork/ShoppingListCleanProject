package com.example.di.example2.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.di.databinding.ActivityMainBinding
import com.example.di.example1.Activity

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
    get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val activity = Activity()
        activity.keyboard.toString()
        activity.mouse.toString()
        activity.monitor.toString()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}