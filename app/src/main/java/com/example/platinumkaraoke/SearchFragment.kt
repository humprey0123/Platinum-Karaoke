package com.example.platinumkaraoke

import android.os.Bundle
import android.view.*
import android.view.KeyEvent
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchFragment : Fragment() {

    private lateinit var adapter: SongAdapter
    private lateinit var allSongs: List<Song>

    private lateinit var activeCategory: LinearLayout
    private lateinit var choiceCategory: LinearLayout
    private lateinit var recycler: RecyclerView
    private lateinit var scrollThumb: View

    // 🔥 ADDED
    private lateinit var searchEditText: TextView
    private lateinit var keyboard: LinearLayout
    private var searchQuery: String = ""

    private var incomingFilter: String? = null
    private var selectedGroupIndex = 0

    // ===============================
    // 🔷 DATA MODEL
    // ===============================
    data class FilterGroup(
        val name: String,
        var active: String,
        val choices: MutableList<String>
    )

    // ===============================
    // 🔷 FILTER DATA
    // ===============================
    private val filterGroups = mutableListOf(
        FilterGroup("Region", "Philippines", mutableListOf("Korea", "China", "Russia", "Brazil", "Vietnam")),
        FilterGroup("View", "Title View", mutableListOf("Artist View")),
        FilterGroup("Category", "All", mutableListOf("Regional", "Kids", "Gospel", "Training")),
        FilterGroup("Date", "New Songs", mutableListOf("<05-2026>")),
        FilterGroup("Genre", "Pop", mutableListOf(
            "English Classics", "OPM Classics", "K-Pop", "Rock", "Slow Rock", "Alternative",
            "Country", "EDM/Techno", "Hip-hop/Rap", "RNB/Soul",
            "Love Song", "Power Ballad", "Reggae/Ska", "Novelty", "Folk"
        )),
        FilterGroup("Playlist", "Playlists", mutableListOf("P1", "P2", "P3"))
    )

    // ===============================
    // 🧱 LIFECYCLE
    // ===============================
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_search, container, false)

        activeCategory = view.findViewById(R.id.active_category_container)
        choiceCategory = view.findViewById(R.id.choice_category_container)
        recycler = view.findViewById(R.id.songRecycler)
        scrollThumb = view.findViewById(R.id.scrollThumb)

        // 🔥 ADDED
        searchEditText = view.findViewById(R.id.search)
        keyboard = view.findViewById(R.id.keyboard_container)

        incomingFilter = arguments?.getString("selected_filter")

        setupRecycler()
        setupScrollbar()
        setupFilters()

        setupKeyboard() // 🔥 ADDED

        loadSongs()

        return view
    }

    // ===============================
    // ⌨️ KEYBOARD LOGIC
    // ===============================
    private fun setupKeyboard() {
        for (i in 0 until keyboard.childCount) {
            val row = keyboard.getChildAt(i)

            if (row is LinearLayout) {
                for (j in 0 until row.childCount) {
                    val keyView = row.getChildAt(j)

                    if (keyView is TextView) {
                        keyView.setOnClickListener {
                            val key = keyView.text.toString()
                            handleKeyPress(key)
                        }
                    }
                }
            }
        }
    }

    private fun handleKeyPress(key: String) {
        when (key) {

            "SPACE" -> searchQuery += " "

            "⌫" -> {
                if (searchQuery.isNotEmpty()) {
                    searchQuery = searchQuery.dropLast(1)
                }
            }

            else -> searchQuery += key
        }

        searchEditText.text = searchQuery

        // 🔥 triggers your existing filter system
        filterSongs()
    }

    // ===============================
    // 🎵 SONG LOGIC
    // ===============================
    private fun loadSongs() {
        val csvReader = CsvReader(requireContext())
        allSongs = csvReader.loadAllSongs()

        incomingFilter?.let { applyIncomingFilter(it) }

        filterSongs()
    }

    private fun filterSongs() {
        val genre = filterGroups.find { it.name == "Genre" }?.active

        val filtered = allSongs.filter { song ->

            val matchesGenre = genre?.let {
                song.genre.equals(mapGenreToFileName(it), true)
            } ?: true

            val matchesSearch =
                song.title.contains(searchQuery, true) ||
                        song.artist.contains(searchQuery, true)

            matchesGenre && matchesSearch
        }

        adapter.submitList(filtered)
    }

    private fun mapGenreToFileName(genre: String): String {
        return when (genre) {
            "K-Pop" -> "kpop"
            "Hip-hop/Rap" -> "hiphop"
            "RNB/Soul" -> "rnb"
            "EDM/Techno" -> "edm"
            "Love Song" -> "love_song"
            "Power Ballad" -> "power_ballad"
            "Slow Rock" -> "slow_rock"
            "Reggae/Ska" -> "reggae"
            "OPM Classics" -> "opm"
            "English Classics" -> "english"
            else -> genre.lowercase()
        }
    }

    // ===============================
    // 🧾 RECYCLER
    // ===============================
    private fun setupRecycler() {
        adapter = SongAdapter()
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
        recycler.setHasFixedSize(true)
    }

    // ===============================
    // 📏 SCROLLBAR
    // ===============================
    private fun setupScrollbar() {

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {

                val offset = rv.computeVerticalScrollOffset()
                val range = rv.computeVerticalScrollRange()
                val extent = rv.computeVerticalScrollExtent()

                if (range - extent <= 0) return

                val proportion = offset.toFloat() / (range - extent)
                val trackHeight = rv.height - scrollThumb.height

                scrollThumb.translationY = trackHeight * proportion

                val visibleRatio = extent.toFloat() / range
                val minHeight = 40 // 👈 adjust this (in px)

                val calculatedHeight = (rv.height * visibleRatio).toInt()
                val newHeight = maxOf(minHeight, calculatedHeight)

                scrollThumb.layoutParams.height = newHeight
                scrollThumb.requestLayout()
            }
        })
    }

    // ===============================
    // 🎯 FILTER UI (UNCHANGED)
    // ===============================
    private fun setupFilters() {
        renderActiveCategories()
        showChoices()
    }

    private fun renderActiveCategories() {
        activeCategory.removeAllViews()

        filterGroups.forEachIndexed { index, group ->

            val tv = createCategoryTextView(group.active, true)

            tv.isSelected = index == selectedGroupIndex

            tv.setOnClickListener {
                selectedGroupIndex = index
                renderActiveCategories()
                showChoices()
            }

            tv.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                        if (choiceCategory.childCount > 0) {
                            choiceCategory.getChildAt(0).requestFocus()
                            return@setOnKeyListener true
                        }
                    }
                }
                false
            }

            activeCategory.addView(tv)
        }
    }

    private fun showChoices() {
        val group = filterGroups[selectedGroupIndex]

        choiceCategory.removeAllViews()

        group.choices.forEach { item ->
            val tv = createCategoryTextView(item, false)

            tv.setOnClickListener {
                swapFilter(item)
            }

            choiceCategory.addView(tv)
        }
    }

    private fun swapFilter(selectedItem: String) {
        val group = filterGroups[selectedGroupIndex]

        val oldActive = group.active

        group.active = selectedItem
        group.choices.remove(selectedItem)
        group.choices.add(oldActive)

        renderActiveCategories()
        showChoices()
        filterSongs()
    }

    private fun applyIncomingFilter(filter: String) {

        for ((index, group) in filterGroups.withIndex()) {

            if (group.active.equals(filter, true)) {
                selectedGroupIndex = index
                break
            }

            val match = group.choices.find { it.equals(filter, true) }
            if (match != null) {
                group.choices.remove(match)
                group.choices.add(group.active)
                group.active = match
                selectedGroupIndex = index
                break
            }
        }

        renderActiveCategories()
        showChoices()

        activeCategory.post {
            activeCategory.getChildAt(selectedGroupIndex)?.requestFocus()
        }
    }

    private fun createCategoryTextView(textValue: String, isActive: Boolean): TextView {
        return TextView(requireContext()).apply {

            text = textValue

            setTextAppearance(
                if (isActive) R.style.CategoryTabs else R.style.ChoiceCategory
            )

            if (isActive) {
                setTextColor(resources.getColorStateList(R.drawable.selector_nav_text, null))
                setBackgroundResource(R.drawable.selector_search_category)
                setPadding(20.dp, 4.dp, 20.dp, 4.dp)
            } else {
                setTextColor(resources.getColorStateList(R.drawable.selector_nav_choice_category, null))
                setPadding(3.dp, 4.dp, 3.dp, 4.dp)
            }

            isFocusable = true
            isFocusableInTouchMode = false
            isClickable = true

            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                marginStart = 8.dp
                marginEnd = 8.dp
            }
        }
    }

    private val Int.dp: Int
        get() = (this * resources.displayMetrics.density).toInt()
}