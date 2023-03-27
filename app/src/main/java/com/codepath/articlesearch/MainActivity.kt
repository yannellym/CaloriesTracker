package com.codepath.articlesearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

private const val TAG = "MainActivity/"

class MainActivity : AppCompatActivity() {
    private lateinit var foodsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val foods = mutableListOf<DisplayFoods>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        foodsRecyclerView = findViewById(R.id.foods)
        val foodAdapter = DisplayArticleAdapter(this, foods)
        foodsRecyclerView.adapter = foodAdapter

        foodsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            foodsRecyclerView.addItemDecoration(dividerItemDecoration)
        }
        // Listen to any changes to items in the database
        // When we have a new list of items to display:
        // Map new items to DisplayArticles
        // Update our UI by passing the new list to our ArticleAdapter.
        lifecycleScope.launch {
            (application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFoods(
                        entity.food,
                        entity.calories,
                    )
                }.also { mappedList ->
                    foods.clear()
                    foods.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }
        // Move the initialization of addFoodButton to this block
        val addFoodButton: Button = findViewById<Button>(R.id.addFood)
        addFoodButton.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }
}

/*
When we make API calls, instead of showing that data, we load that data into the database.
The database becomes our single source of truth.
* 2 - We need to update any articleDao interaction to run on another thread.
We can use Kotlin coroutines for this. They can be a lightweight and simple way to
"launch" some code to run asynchronously on a different thread. In this case,
we want to attach this coroutine to the lifecycle of our View. Any coroutine
launched in this scope is cancelled when the Lifecycle is destroyed.
To specify where the coroutines should run, Kotlin provides three dispatchers that you can use:

Dispatchers.Main - Use this dispatcher to run a coroutine on the main Android thread. This should
be used only for interacting with the UI and performing quick work. Examples include calling suspend
functions, running Android UI framework operations, and updating LiveData objects. The default if no
dispatcher is specified. Dispatchers.IO - This dispatcher is optimized to perform disk or network
I/O outside of the main thread. Examples include using the Room component, reading from or writing
to files, and running any network operations.
Dispatchers.Default - This dispatcher is optimized to perform CPU-intensive work outside of the
main thread. Example use cases include sorting a list and parsing JSON.
Since we are using Room and writing to files, we want to use the IO Dispatcher.
*/