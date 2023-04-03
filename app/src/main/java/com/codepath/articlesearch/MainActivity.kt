package com.codepath.articlesearch

import FoodListFragment
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

private const val TAG = "MainActivity/"

class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE_ADD_FOOD = 1
        const val FOODS_EXTRA = "FOODS_EXTRA"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager
//
//        // define your fragments here
        val foodListFragment: Fragment = FoodListFragment()
        //val foodDashFragment: Fragment = FoodListFragment()

          val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
//
//        // handle navigation selection
          bottomNavigationView.setOnItemSelectedListener { item ->
              lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.food_log -> fragment = foodListFragment
            }
            replaceFragment(fragment)
            true
        }

//        // Set default selection
         bottomNavigationView.selectedItemId = R.id.calories_sub

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