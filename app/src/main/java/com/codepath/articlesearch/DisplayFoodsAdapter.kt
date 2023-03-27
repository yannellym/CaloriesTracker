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

class DisplayArticleAdapter(private val context: Context, private val foods: List<DisplayFoods>) :
    RecyclerView.Adapter<DisplayArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_single, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        holder.bind(food)
    }

    override fun getItemCount() = foods.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val titleTextView = itemView.findViewById<TextView>(R.id.mediaTitle)
        private val abstractTextView = itemView.findViewById<TextView>(R.id.mediaAbstract)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(food: DisplayFoods) {
            titleTextView.text = food.name
            abstractTextView.text = food.calories.toString()
            // Hide the button and user input fields
            itemView.findViewById<View>(R.id.submit_button).visibility = View.GONE
            itemView.findViewById<View>(R.id.user_title).visibility = View.GONE
            itemView.findViewById<View>(R.id.user_message).visibility = View.GONE
        }

        override fun onClick(v: View?) {
            // Get selected food
            val food = foods[absoluteAdapterPosition]

            //  Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(FOODS_EXTRA, food as Parcelable)
            context.startActivity(intent)
        }
    }
}