package com.codepath.bitfit

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.codepath.bitbit.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.coroutines.flow.collect

private const val TAG = "FoodDashFragment"

class FoodDashFragment : Fragment() {

    private lateinit var caloriesTextView: TextView
    private lateinit var foodsTextView: TextView
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_food_dash, container, false)

        caloriesTextView = view.findViewById(R.id.user_calories)
        foodsTextView = view.findViewById(R.id.user_food)
        pieChart = view.findViewById(R.id.chart)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            (requireActivity().application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                val totalCalories = databaseList.sumOf{ it.calories!! }
                val totalFoods = databaseList.size

                caloriesTextView.text = "Total Calories: $totalCalories"
                foodsTextView.text = "Total Foods: $totalFoods"

                val pieEntries = databaseList
                    .groupBy { it.name }
                    .mapValues { it.value.sumOf { food -> food.calories!! } }
                    .filter { it.value > 0 }
                    .map { PieEntry(it.value.toFloat(), it.key) }



                if (pieEntries.isNotEmpty()) {
                    val pieDataSet = PieDataSet(pieEntries as MutableList<PieEntry>?, "Food Intake")
                    pieDataSet.colors = mutableListOf(
                        Color.rgb(255, 69, 0),
                        Color.rgb(255, 165, 0),
                        Color.rgb(255, 215, 0),
                        Color.rgb(50, 205, 50),
                        Color.rgb(0, 191, 255),
                        Color.rgb(0, 0, 139)
                    )

                    val pieData = PieData(pieDataSet)
                    pieData.setValueFormatter(PercentFormatter(pieChart))
                    pieData.setValueTextColor(Color.WHITE)

                    pieChart.data = pieData
                    pieChart.description.isEnabled = false
                    pieChart.isRotationEnabled = false
                    pieChart.centerText = "Food Intake"
                    pieChart.setCenterTextSize(20f)
                    pieChart.setCenterTextColor(Color.BLACK)
                    pieChart.legend.isEnabled = true
                    pieChart.animateY(1000)
                    pieChart.invalidate()
                } else {
                    pieChart.visibility = View.GONE
                }
            }
        }
    }
}