package com.codepath.articlesearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "DetailActivity"

//This Activity will be responsible for showing more information about the book that was selected
//from the list of articles. The new screen will show the article's title, author, a short summary
//of the article, and an image (if there is one to show).
class DetailActivity : AppCompatActivity() {
    private lateinit var foodTextView: TextView
    private lateinit var caloriesTextView: TextView
    private lateinit var submitButton: Button
    private lateinit var userFood: TextView
    private lateinit var userCalories: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_single)
        submitButton = findViewById(R.id.submit_button)
        userFood = findViewById<TextView>(R.id.user_title)
        userCalories = findViewById<TextView>(R.id.user_message)

        submitButton.setOnClickListener {
            val food = userFood.text?.toString()
            val calories = userCalories.text?.toString()

            val intent = Intent()
            intent.putExtra("food", food)
            intent.putExtra("calories", calories)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}