package com.codepath.articlesearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

private const val TAG = "MainActivity/"

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_ADD_FOOD = 1
        const val FOODS_EXTRA = "FOODS_EXTRA"
    }
//    // declare the variables that will be used
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: DisplayFoodsAdapter
//    private lateinit var layoutManager: LinearLayoutManager
//    private lateinit var addButton: Button
//    private lateinit var foods: MutableList<DisplayFoods>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        // Initialize RecyclerView
//        recyclerView = findViewById(R.id.foods)
//        layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = layoutManager
//
//        // Initialize list of foods
//        foods = mutableListOf()
//        adapter = DisplayFoodsAdapter(this, foods)
//        recyclerView.adapter = adapter
//
//        // Initialize FAB and set click listener
//        addButton = findViewById(R.id.addFood)
//        addButton.setOnClickListener {
//            val intent = Intent(this, DetailActivity::class.java)
//            startActivityForResult(intent, REQUEST_CODE_ADD_FOOD)
//        }
//    }
//
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == REQUEST_CODE_ADD_FOOD && resultCode == Activity.RESULT_OK && data != null) {
//            val food = data.getParcelableExtra<DisplayFoods>(FOODS_EXTRA)
//
//            // Add new food to list and update RecyclerView if its not nukk
//            if (food != null) {
//                foods.add(food)
//            }
//            // updates the adapter letting it know that the data changed
//            adapter.notifyDataSetChanged()
//        }
    }
}