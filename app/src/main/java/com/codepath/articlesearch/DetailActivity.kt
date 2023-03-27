package com.codepath.articlesearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.codepath.articlesearch.databinding.ActivityDetailBinding

private const val TAG = "DetailActivity"

/*
this will display the calories and food textViews. It will also allow the user to enter the chosen
food and calories. Then, submit upon being done. The submit button has a click listener that send the info to the main screen.
*/
class DetailActivity : AppCompatActivity() {
    private lateinit var submitButton: Button
    private lateinit var userFood: TextView
    private lateinit var userCalories: TextView
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(R.layout.item_single)
        submitButton = findViewById(R.id.submit_button)
        userFood = findViewById(R.id.user_title)
        userCalories = findViewById(R.id.user_message)

        submitButton.setOnClickListener {
            val name = userFood.text.toString()
            val calories = userCalories.text.toString()

            val food = DisplayFoods(name, calories)
            // our intent to go to the main page
            val intent = Intent()
            intent.putExtra(FOODS_EXTRA, food)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}