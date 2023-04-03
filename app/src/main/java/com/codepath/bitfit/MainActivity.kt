package com.codepath.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codepath.bitFit.R
import com.codepath.bitFit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "MainActivity/"


class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_ADD_FOOD = 1
        const val FOODS_EXTRA = "FOODS_EXTRA"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var addButton: Button
    private lateinit var foodListFragment: FoodListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // pass the list of foods to the com.codepath.bitfit.FoodListFragment
        foodListFragment = FoodListFragment()

        // Set the com.codepath.bitfit.FoodListFragment as the default fragment to show
        replaceFragment(foodListFragment)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.food_log -> foodListFragment
                else -> foodListFragment // default fragment to show
            }
            replaceFragment(fragment)
            true
        }
        // Set default selection
        bottomNavigationView.selectedItemId = R.id.food_log

        // Initialize FAB and set click listener
        addButton = findViewById(R.id.addFood)
        addButton.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_FOOD)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.food_frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
