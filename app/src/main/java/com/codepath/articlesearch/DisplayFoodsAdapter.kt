package com.codepath.articlesearch

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val FOODS_EXTRA = "FOODS_EXTRA"
private const val TAG = "DisplayFoodsAdapter"

class DisplayFoodsAdapter(private val context: Context, var foods: MutableList<DisplayFoods>) :
    RecyclerView.Adapter<DisplayFoodsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        holder.bind(food)
    }

    override fun getItemCount() = foods.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        // whenever the screen is loaded, it will load the foods and calories in the database
        private val titleTextView = itemView.findViewById<TextView>(R.id.user_food)
        private val abstractTextView = itemView.findViewById<TextView>(R.id.user_calories)

        init {
            itemView.setOnClickListener(this)
        }
        /*
        will display the name of the foods and calories that were inputted
        */
        fun bind(food: DisplayFoods) {
            titleTextView.text = food.name
            abstractTextView.text = food.calories.toString()
            itemView.findViewById<View>(R.id.calories_sub).visibility = View.VISIBLE
        }
        /*
        whenever the add food button is clicked, it will take you to the second screen where
        you get to input the food and calories
        */
        override fun onClick(v: View?) {

            val food = foods[absoluteAdapterPosition]

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(FOODS_EXTRA, food as Parcelable)
            context.startActivity(intent)
        }
    }
}