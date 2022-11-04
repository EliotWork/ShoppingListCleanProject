package com.example.bitok.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bitok.R
import com.example.bitok.databinding.MainActivityBinding
import com.example.bitok.presentation.fragment.CoinPriceListFragment

class MainActivity : AppCompatActivity() {
    private var _binding: MainActivityBinding? = null
    private val binding: MainActivityBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,CoinPriceListFragment.newInstance())
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
