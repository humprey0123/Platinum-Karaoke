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

    private lateinit var activeCategory: LinearLayout
    private lateinit var choiceCategory: LinearLayout
    private var incomingFilter: String? = null

    // 🔷 Data model
    data class FilterGroup(
        val name: String,
        var active: String,
        val choices: MutableList<String>
    )

    // 🔷 State
    private val filterGroups = mutableListOf(
        FilterGroup("Region", "Philippines", mutableListOf("Korea", "China", "Russia", "Brazil", "Vietnam")),
        FilterGroup("View", "Title View", mutableListOf("Artist View")),
        FilterGroup("Category", "All", mutableListOf("Regional", "Kids", "Gospel", "Training")),
        FilterGroup("Date", "New Songs", mutableListOf("<05-2026>")),
        FilterGroup("Genre", "Pop", mutableListOf(
            "English Classics", "K-Pop", "Rock", "Slow Rock", "Alternative",
            "Country", "EDM/Techno", "Hip-hop/Rap", "RNB/Soul",
            "Love Song", "Power Ballad", "Reggae/Ska", "Novelty", "Folk"
        )),
        FilterGroup("Playlist", "Playlists", mutableListOf("P1", "P2", "P3"))
    )

    private var selectedGroupIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        activeCategory = view.findViewById(R.id.active_category_container)
        choiceCategory = view.findViewById(R.id.choice_category_container)
        incomingFilter = arguments?.getString("selected_filter")
        setupUI()
        incomingFilter?.let { filter ->
            applyIncomingFilter(filter)
        }

        return view
    }

    private fun applyIncomingFilter(filter: String) {
        filterGroups.forEachIndexed { index, group ->
            if (group.choices.contains(filter)) {

                selectedGroupIndex = index

                // swap it into active
                group.choices.remove(filter)
                group.choices.add(group.active)
                group.active = filter

                // refresh UI
                renderActiveCategories()
                showChoices()

                // 🔥 FOCUS ACTIVE ITEM
                activeCategory.post {
                    val view = activeCategory.getChildAt(index)
                    view?.requestFocus()
                }

                return
            }
        }
    }

    // 🔷 Initial setup
    private fun setupUI() {
        renderActiveCategories()
        showChoices()
    }

    // 🔝 Render active filters (top container)
    private fun renderActiveCategories() {
        activeCategory.removeAllViews()

        filterGroups.forEachIndexed { index, group ->
            val tv = createCategoryTextView(group.active, isActive = true)

            tv.setOnClickListener {
                selectedGroupIndex = index
                showChoices()
            }

            activeCategory.addView(tv)
        }
    }

    // 🔽 Show choices for selected group (bottom container)
    private fun showChoices() {
        val group = filterGroups[selectedGroupIndex]

        choiceCategory.removeAllViews()

        group.choices.forEach { item ->
            val tv = createCategoryTextView(item, isActive = false)

            tv.setOnClickListener {
                swapFilter(item)
            }

            choiceCategory.addView(tv)
        }
    }

    // 🔁 Swap logic (core behavior)
    private fun swapFilter(selectedItem: String) {
        val group = filterGroups[selectedGroupIndex]

        val oldActive = group.active

        // swap values
        group.active = selectedItem
        group.choices.remove(selectedItem)
        group.choices.add(oldActive)

        // refresh UI
        renderActiveCategories()
        showChoices()
    }

    // 🔧 Reusable TextView creator
    private fun createCategoryTextView(textValue: String, isActive: Boolean): TextView {
        return TextView(requireContext()).apply {
            text = textValue

            setTextAppearance(
                if (isActive) R.style.CategoryTabs else R.style.ChoiceCategory
            )

            setTextColor(resources.getColorStateList(R.drawable.selector_nav_text, null))

            if (isActive) {
                setBackgroundResource(R.drawable.selector_search_category)
                setPadding(20.dp, 4.dp, 20.dp, 4.dp)
            } else {
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

    // 📏 DP helper
    private val Int.dp: Int
        get() = (this * resources.displayMetrics.density).toInt()
}