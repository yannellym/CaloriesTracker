import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.*
import kotlinx.coroutines.launch

private const val TAG = "FoodListFragment"

class FoodListFragment : Fragment() {
    private lateinit var dao: FoodDao
    private lateinit var foods: MutableList<DisplayFoods>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DisplayFoodsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dao = AppDatabase.getInstance(requireContext()).foodDao()
        foods = mutableListOf()
        adapter = DisplayFoodsAdapter(requireContext(), foods)

        val view = inflater.inflate(R.layout.fragment_food_list, container, false)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.food_recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize foods here
        foods = mutableListOf()

        lifecycleScope.launch {
            // Fetch the data and update the list
            dao.getAll().collect { data ->
                val foods = data.map { foodEntity ->
                    Log.d(TAG, "Inserted food: ${foodEntity.name} (${foodEntity.calories} calories)")
                    DisplayFoods(
                        foodEntity.name,
                        foodEntity.calories,
                    )
                }
                adapter.foods = foods as MutableList<DisplayFoods>
                adapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        fun newInstance(): FoodListFragment {
            return FoodListFragment()
        }
    }
}