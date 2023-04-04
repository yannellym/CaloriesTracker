package com.codepath.bitfit

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codepath.bitbit.R
import com.codepath.bitbit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "MainActivity/"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // fragments that will be used
        val foodListFragment: Fragment = FoodListFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.food_log -> fragment = foodListFragment
            }
            replaceFragment(fragment)
            true
        }
        // Set default selection
        bottomNavigationView.selectedItemId = R.id.food_log
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.food_frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
