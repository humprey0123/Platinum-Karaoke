package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment

class SearchFragment : Fragment() {

    private lateinit var mainContainer: LinearLayout
    private lateinit var subContainer: LinearLayout

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

        mainContainer = view.findViewById(R.id.mainCategoryContainer)
        subContainer = view.findViewById(R.id.subCategoryContainer)

        setupMainCategories()

        return view
    }

    // 🔹 Create main categories
    private fun setupMainCategories() {
        categoryMap.keys.forEach { category ->

            val tv = createCategoryTextView(category, isMain = true)

            tv.setOnClickListener {
                    updateSubCategories(categoryMap[category] ?: emptyList())

            }


            mainContainer.addView(tv)
        }

        // 🔥 DEFAULT: Philippines
        val defaultCategory = "Philippines"

        val index = categoryMap.keys.indexOf(defaultCategory)
        if (index != -1) {
            val defaultView = mainContainer.getChildAt(index)

            // Focus it
            defaultView.requestFocus()

            // Trigger its content manually
            updateSubCategories(categoryMap[defaultCategory] ?: emptyList())
        }

        // Auto focus first
        if (mainContainer.childCount > 0) {
            mainContainer.getChildAt(0).requestFocus()
        }
    }

    // 🔹 Update sub categories
    private fun updateSubCategories(subCategories: List<String>) {
        subContainer.removeAllViews()

        subCategories.forEach { sub ->
            val tv = createCategoryTextView(sub, isMain = false)
            subContainer.addView(tv)
        }
    }

    // 🔥 Reusable TextView creator (FIXED margins + dp)
    private fun createCategoryTextView(textValue: String, isMain: Boolean): TextView {
        return TextView(requireContext()).apply {
            text = textValue

            setTextAppearance(
                if (isMain) R.style.CategoryTabs else R.style.SubCategory
            )

            setTextColor(resources.getColorStateList(R.drawable.selector_nav_text, null))

            if (isMain) {
                setBackgroundResource(R.drawable.selector_search_category)

                // 🔥 Main category padding
                setPadding(20.dp, 4.dp, 20.dp, 4.dp)
            } else {
                // 🔥 Subcategory padding (INLINE = 10dp)
                setPadding(10.dp, 4.dp, 10.dp, 4.dp)
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

    // 🔥 DP extension (clean)
    private val Int.dp: Int
        get() = (this * resources.displayMetrics.density).toInt()
}