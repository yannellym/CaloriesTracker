package com.codepath.bitfit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.bitbit.R
import kotlinx.coroutines.launch

private const val TAG = "FoodListFragment"


class FoodListFragment : Fragment() {
    // Initialize foods here
    private val foods = mutableListOf<FoodEntity>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var displayFoodsAdapter: DisplayFoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "showing fragment")
        // Call the new method within onCreate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, foods.isEmpty().toString())
        val view = inflater.inflate(R.layout.fragment_food_list, container, false)

        // Define RecyclerView and Adapter
        recyclerView = view.findViewById(R.id.recyclerView)
        displayFoodsAdapter = DisplayFoodsAdapter(requireContext(), foods)
        recyclerView.adapter = displayFoodsAdapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext()).also {
            val dividerItemDecoration = DividerItemDecoration(requireContext(), it.orientation)
            recyclerView.addItemDecoration(dividerItemDecoration)
        }

        Log.d(TAG, "inside fragment")
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            (requireActivity().application as FoodApplication).db.foodDao().getAll()
                .collect { databaseList ->
                    Log.d(TAG, "looking through database")
                    foods.clear()
                    databaseList.map { mappedList ->
                        foods.addAll(listOf(mappedList))
                        displayFoodsAdapter.notifyDataSetChanged()
                   }
                }
            }
        recyclerView.layoutManager = LinearLayoutManager(requireContext()).also {
            val dividerItemDecoration = DividerItemDecoration(requireContext(), it.orientation)
            recyclerView.addItemDecoration(dividerItemDecoration)
        }
        //Calling an intent on Button Click
        val addButton = view.findViewById<Button>(R.id.addFood)
        addButton.setOnClickListener {
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            startActivity(intent)
        }
    }
}