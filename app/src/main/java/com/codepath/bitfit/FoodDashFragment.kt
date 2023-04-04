package com.codepath.bitfit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.codepath.bitbit.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


private const val TAG = "FoodDashFragment"


class DashFragment : Fragment() {

    private lateinit var caloriesTextView: TextView
    private lateinit var foodsTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_food_dash, container, false)

        caloriesTextView = view.findViewById(R.id.user_food)
        foodsTextView = view.findViewById(R.id.user_calories)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            (requireActivity().application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                val totalCalories = databaseList.sumBy { it.calories?.toInt() ?: 0 }
                val totalFoods = databaseList.size

                caloriesTextView.text = "Total Calories: $totalCalories"
                foodsTextView.text = "Total Foods: $totalFoods"
            }
        }
    }
}