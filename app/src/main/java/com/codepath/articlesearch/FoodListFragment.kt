package com.codepath.articlesearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "FoodListFragment"

class FoodListFragment : Fragment() {
    private lateinit var foods: MutableList<DisplayFoods>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DisplayFoodsAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_list, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.food_recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = DisplayFoodsAdapter(view.context, foods)
        recyclerView.adapter = adapter
        // Update the return statement to return the inflated view from above
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MainActivity.REQUEST_CODE_ADD_FOOD && resultCode == Activity.RESULT_OK && data != null) {
            val food = data.getParcelableExtra<DisplayFoods>(MainActivity.FOODS_EXTRA)

            // Add new food to list and update RecyclerView if its not null
            if (food != null) {
                foods.add(food)
            }
            // updates the adapter letting it know that the data changed
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance(): FoodListFragment {
            return FoodListFragment()
        }
    }
}