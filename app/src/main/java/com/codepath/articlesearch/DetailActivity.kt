package com.codepath.articlesearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.codepath.articlesearch.databinding.ActivityDetailBinding

private const val TAG = "DetailActivity"

//This Activity will be responsible for showing more information about the book that was selected
//from the list of articles. The new screen will show the article's title, author, a short summary
//of the article, and an image (if there is one to show).
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

            val intent = Intent()
            intent.putExtra(FOODS_EXTRA, food)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}