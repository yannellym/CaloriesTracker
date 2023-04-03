import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.bitFit.R
import com.codepath.bitfit.*
import kotlinx.coroutines.launch

private const val TAG = "FoodListFragment"

class FoodListFragment : Fragment() {
    // Initialize foods here
    private val foods = mutableListOf<Food>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var displayFoodsAdapter: DisplayFoodsAdapter
    private lateinit var dao: FoodDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = AppDatabase.getInstance(requireContext()).foodDao()
        Log.d(TAG, "showing fragment")
        // Call the new method within onCreate
        fetchFoods()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_list, container, false)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.food_recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        displayFoodsAdapter = DisplayFoodsAdapter(view.context, foods)
        recyclerView.adapter = displayFoodsAdapter
        return view

    }

    private fun fetchFoods() {
        lifecycleScope.launch {
            // Fetch the data and update the list
            dao.getAll().collect { data ->
                foods.addAll(data.map { foodEntity ->
                    Log.d(TAG, "Inserted food: ${foodEntity.name} (${foodEntity.calories} calories)")
                    Food(
                        foodEntity.name,
                        foodEntity.calories,
                    )
                }
                )
                displayFoodsAdapter.foods = foods.toMutableList()
                displayFoodsAdapter.notifyDataSetChanged()
            }
        }
    }


    companion object {
        fun newInstance(): FoodListFragment {
            return FoodListFragment()
        }
    }
}