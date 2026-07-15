package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.google.android.flexbox.FlexboxLayout

class SearchFragment : Fragment() {

    private lateinit var activeCategory: LinearLayout
    private lateinit var choiceCategory: LinearLayout

    private val categoryMap = mapOf(
        "Philippines" to listOf("Korea", "China", "Russia", "Brazil", "Vietnam"),
        "Title View" to listOf("Artist View"),
        "All" to listOf("Regional", "Kids", "Gospel", "Training"),
        "New Songs" to listOf("<05-2026>"),
        "Pop" to listOf("English Classics", "K-Pop", "Rock", "Slow Rock", "Alternative", "Country", "Pop", "EDM/Techno", "Hiphop/Rap", "RNB/Soul", "Love Song", "Power Ballad", "Reggae/Ska", "Novelty", "Folk"),
        "Playlists" to listOf("P1", "P2", "P3")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        activeCategory = view.findViewById(R.id.active_category_container)
        choiceCategory = view.findViewById(R.id.choice_category_container)

        setupActiveCategories()

        return view
    }

    // Create main categories
    private fun setupActiveCategories() {
        categoryMap.keys.forEach { category ->

            val tv = createCategoryTextView(category, isActive = true)

            tv.setOnClickListener {
                    updateChoiceCategory(categoryMap[category] ?: emptyList())

            }


            activeCategory.addView(tv)
        }

        // DEFAULT: Philippines
        val activeCategoryName = "Philippines"

        val index = categoryMap.keys.indexOf(activeCategoryName)
        if (index != -1) {
            val defaultView = activeCategory.getChildAt(index)

            defaultView.requestFocus()
            updateChoiceCategory(categoryMap[activeCategoryName] ?: emptyList())
        }

    }

    // Update sub categories
    private fun updateChoiceCategory(choiceItems: List<String>) {
        choiceCategory.removeAllViews()

        choiceItems.forEach { item ->
            val tv = createCategoryTextView(item, isActive = false)
            choiceCategory.addView(tv)
        }
    }

    // Reusable TextView creator (FIXED margins + dp)
    private fun createCategoryTextView(textValue: String, isActive: Boolean): TextView {
        return TextView(requireContext()).apply {
            text = textValue

            setTextAppearance(
                if (isActive) R.style.CategoryTabs else R.style.SubCategory
            )

            setTextColor(resources.getColorStateList(R.drawable.selector_nav_text, null))

            if (isActive) {
                setBackgroundResource(R.drawable.selector_search_category)

                // Active category padding
                setPadding(20.dp, 4.dp, 20.dp, 4.dp)
            } else {
                // Subcategory padding (INLINE = 10dp)
                setPadding(3.dp, 4.dp, 3.dp, 4.dp)
            }

            isFocusable = true
            isFocusableInTouchMode = true

            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                marginStart = 8.dp
                marginEnd = 8.dp
            }
        }
    }

    // DP extension (clean)
    private val Int.dp: Int
        get() = (this * resources.displayMetrics.density).toInt()
}
