package com.codepath.bitfit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.bitFit.R
import kotlinx.coroutines.launch

private const val TAG = "FoodListFragment"


class FoodListFragment : Fragment(), OnListFragmentInteractionListener  {
    // Initialize foods here
    private val foods = mutableListOf<Food>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var displayFoodsAdapter: DisplayFoodsAdapter
    private lateinit var foodDao: FoodDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "showing fragment")
        // Call the new method within onCreate

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        foodDao = AppDatabase.getInstance(requireContext()).foodDao()
        val view = inflater.inflate(R.layout.fragment_food_list, container, false)
        recyclerView = view.findViewById(R.id.food_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        displayFoodsAdapter = DisplayFoodsAdapter(requireContext(), foods)
        recyclerView.adapter = displayFoodsAdapter

        fetchFoods()
        return view
    }

    private fun fetchFoods() {
        lifecycleScope.launch {
            // Fetch the data and update the list
            foodDao.getAll().collect { data ->
                foods.clear()
                foods.addAll(data.map { foodEntity ->
                    Log.d(TAG, "Inserted food: ${foodEntity.name} (${foodEntity.calories} calories)")
                    Food(
                        foodEntity.name,
                        foodEntity.calories,
                    )
                })
                displayFoodsAdapter.foods = foods.toMutableList() // update adapter with new data
                displayFoodsAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onItemClick(item: Food) {
        Toast.makeText(context, "test: " + item.name + item.calories, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(): FoodListFragment {
            return FoodListFragment()
        }
    }
}