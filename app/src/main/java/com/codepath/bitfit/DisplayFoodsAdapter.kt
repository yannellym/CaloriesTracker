package com.codepath.bitfit

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codepath.bitbit.R

private const val TAG = "DisplayFoodsAdapter"

class DisplayFoodsAdapter( private val context: Context, var foods: MutableList<FoodEntity>):
    RecyclerView.Adapter<DisplayFoodsAdapter.ViewHolder>() {

    // Listener member variable
    private lateinit var mListener : OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position:Int)
    }
    //Define method to allow parent activity/fragment to define the listener
    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_single, parent, false)
        return ViewHolder(view, mListener)
    }
    inner class ViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.user_food)
        private val calories: TextView = itemView.findViewById(R.id.user_calories)

        init {
            itemView.setOnLongClickListener {
                listener.onItemClick(adapterPosition)
                true
            }
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method
        fun bind(food: FoodEntity) {
            name.text = food.name
            calories.text = food.calories.toString()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        holder.bind(food)
        Log.d(TAG, "Binding food item at position $position")
    }

    override fun getItemCount(): Int {
        return foods.size
    }
}

