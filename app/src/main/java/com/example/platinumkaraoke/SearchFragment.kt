package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class SearchFragment : Fragment() {

    private lateinit var mainContainer: LinearLayout
    private lateinit var subContainer: LinearLayout

    // 🔥 Your dynamic data
    private val categoryMap = mapOf(
        "Philippines" to listOf("Korea", "China", "Russia", "Brazil", "Vietnam"),
        "Title View" to listOf("Top Hits", "Trending", "Classic"),
        "All" to listOf("A-Z", "Recent", "Popular"),
        "New Songs" to listOf("2024", "2025"),
        "Pop" to listOf("K-Pop", "P-Pop", "J-Pop"),
        "Playlists" to listOf("Workout", "Chill", "Love Songs")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        mainContainer = view.findViewById(R.id.mainCategoryContainer)
        subContainer = view.findViewById(R.id.subCategoryContainer)

        setupMainCategories()

        return view
    }

    // 🔹 Create main categories dynamically
    private fun setupMainCategories() {
        categoryMap.keys.forEach { category ->

            val tv = TextView(requireContext()).apply {
                text = category
                setTextAppearance(R.style.CategoryTabs)
                setBackgroundResource(R.drawable.selector_search_category)
                setTextColor(resources.getColorStateList(R.drawable.selector_nav_text, null))

                isFocusable = true
                isFocusableInTouchMode = true

                setPadding(20, 10, 20, 10)
            }

            // 🔥 TV Focus = trigger update
            tv.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    updateSubCategories(categoryMap[category] ?: emptyList())
                }
            }

            mainContainer.addView(tv)
        }

        // Optional: auto-select first category
        if (mainContainer.childCount > 0) {
            mainContainer.getChildAt(0).requestFocus()
        }
    }

    // 🔹 Update sub categories dynamically
    private fun updateSubCategories(subCategories: List<String>) {
        subContainer.removeAllViews()

        subCategories.forEach { sub ->

            val tv = TextView(requireContext()).apply {
                text = sub
                setTextAppearance(R.style.SubCategory)
                setTextColor(resources.getColorStateList(R.drawable.selector_nav_text, null))

                isFocusable = true
                isFocusableInTouchMode = true

                setPadding(20, 10, 20, 10)
            }

            subContainer.addView(tv)
        }
    }
}