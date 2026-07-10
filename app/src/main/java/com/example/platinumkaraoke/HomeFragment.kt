package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // 👉 ADD YOUR GRID LOGIC HERE
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.gridRecycler)


        // 4 columns
        val layoutManager = GridLayoutManager(requireContext(), 4)
        recyclerView.layoutManager = layoutManager

        // sample 16 items (4x4 grid)
        val items = listOf(
            GridAdapter.Category(R.drawable.home_cat_1opm),
            GridAdapter.Category(R.drawable.home_cat_2pop),
            GridAdapter.Category(R.drawable.home_cat_3slowrock),
            GridAdapter.Category(R.drawable.home_cat_4english_classics),
            GridAdapter.Category(R.drawable.home_cat_5k_pop),
            GridAdapter.Category(R.drawable.home_cat_6alternative),
            GridAdapter.Category(R.drawable.home_cat_7country),
            GridAdapter.Category(R.drawable.home_cat_8rock),
            GridAdapter.Category(R.drawable.home_cat_9edm_techno),
            GridAdapter.Category(R.drawable.home_cat_10hiphop_rap),
            GridAdapter.Category(R.drawable.home_cat_11rnd_soul),
            GridAdapter.Category(R.drawable.home_cat_12love_song),
            GridAdapter.Category(R.drawable.home_cat_13power_ballad),
            GridAdapter.Category(R.drawable.home_cat_14raggae_ska),
            GridAdapter.Category(R.drawable.home_cat_15novelty),
            GridAdapter.Category(R.drawable.home_cat_16folk)
        )

        recyclerView.adapter = GridAdapter(items)

        // TV focus settings
        recyclerView.setHasFixedSize(true)
        recyclerView.isFocusable = true
        recyclerView.isFocusableInTouchMode = true
    }

}

// 👉 Adapter class
class GridAdapter(private val items: List<Category>) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    data class Category(
        val imageRes: Int
    )

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.itemImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.image.setImageResource(item.imageRes)

        // ✅ ADD THIS
        holder.itemView.setOnFocusChangeListener { v, hasFocus ->
            v.animate()
                .scaleX(if (hasFocus) 1.1f else 1.0f)
                .scaleY(if (hasFocus) 1.1f else 1.0f)
                .translationZ(if (hasFocus) 80f else 0f) // 👈 SHADOW
                .setDuration(150)
                .start()
        }
    }

    override fun getItemCount() = items.size
}
