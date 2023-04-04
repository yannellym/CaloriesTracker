package com.codepath.bitfit

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.codepath.bitFit.R

private const val TAG = "DisplayFoodsAdapter"

class DisplayFoodsAdapter(
    private val context: Context,
    var foods: MutableList<FoodEntity>,
) : RecyclerView.Adapter<DisplayFoodsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_single, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        holder.name.text = food.name
        holder.calories.text = food.calories
        Log.d(TAG, "Binding food item at position $position")

    }

    override fun getItemCount(): Int {
        return foods.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.user_food)
        val calories: TextView = itemView.findViewById(R.id.user_calories)

        init {


//            itemView.setOnClickListener {
//                val food = foods[adapterPosition]
//                Log.d(TAG, "Clicked on food: ${food.name}")
//                // You can add an Intent here to launch a new activity with more details about the food
  //          }
        }
    }
}

