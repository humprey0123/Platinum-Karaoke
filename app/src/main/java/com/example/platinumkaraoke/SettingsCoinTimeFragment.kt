package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SettingsCoinTimeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_settings_coin_time,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coinTime = view.findViewById<View>(R.id.fragment_settings_coin_time)
        coinTime.requestFocus()

        // Coin Per Song Group
        setupGroupSelection(
            listOf(
                R.id.coin_per_song_none,
                R.id.coin_per_song_1,
                R.id.coin_per_song_2,
                R.id.coin_per_song_3,
                R.id.coin_per_song_4,
                R.id.coin_per_song_5
            ),
            R.id.coin_per_song_1 // Default
        )

        // Song Per Coin Group
        setupGroupSelection(
            listOf(
                R.id.song_per_coin_none,
                R.id.song_per_coin_1,
                R.id.song_per_coin_2,
                R.id.song_per_coin_3,
                R.id.song_per_coin_4,
                R.id.song_per_coin_5
            ),
            R.id.song_per_coin_none // Default
        )

        // Time Bound Group
        setupGroupSelection(
            listOf(
                R.id.time_bound_none,
                R.id.time_bound_30min,
                R.id.time_bound_1hr,
                R.id.time_bound_2hrs,
                R.id.time_bound_4hrs,
                R.id.time_bound_8hrs
            ),
            R.id.time_bound_none // Default
        )

        // Time Per Coin Group
        setupGroupSelection(
            listOf(
                R.id.time_per_coin_none,
                R.id.time_per_coin_15min,
                R.id.time_per_coin_30min,
                R.id.time_per_coin_1hr,
                R.id.time_per_coin_2hr,
                R.id.time_per_coin_3hr
            ),
            R.id.time_per_coin_none // Default
        )
    }

    private fun setupGroupSelection(viewIds: List<Int>, defaultId: Int) {
        val views = viewIds.mapNotNull { view?.findViewById<View>(it) }

        views.forEach { view ->
            view.setOnClickListener {
                updateSelection(views, it)
            }
        }

        // Set initial selection
        view?.findViewById<View>(defaultId)?.let {
            updateSelection(views, it)
        }
    }

    private fun updateSelection(group: List<View>, selectedView: View) {
        group.forEach { it.isSelected = (it == selectedView) }
    }
}