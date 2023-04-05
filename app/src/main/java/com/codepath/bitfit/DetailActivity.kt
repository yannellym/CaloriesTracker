package com.codepath.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.codepath.bitbit.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "DetailActivity"

/*
this will display the calories and food textViews. It will also allow the user to enter the chosen
food and calories. Then, submit upon being done. The submit button has a click listener that send the info to the main screen.
*/
class DetailActivity : AppCompatActivity() {
    private lateinit var submitButton: Button
    private lateinit var userFood: TextView
    private lateinit var userCalories: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.detail_activity)
        submitButton = findViewById(R.id.submit_button)
        userFood = findViewById(R.id.user_title)
        userCalories = findViewById(R.id.user_message)



        submitButton.setOnClickListener {
            val name = userFood.text.toString()
            val calories = userCalories.text.toString()

            lifecycleScope.launch(Dispatchers.IO) {
                (application as FoodApplication).db.foodDao().insert(
                    FoodEntity(name, calories.toInt())
                )
            }
            // our intent to go to the main page
            val i = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(i)
        }
    }
}