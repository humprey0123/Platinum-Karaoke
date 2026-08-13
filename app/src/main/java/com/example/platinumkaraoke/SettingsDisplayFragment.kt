package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SettingsDisplayFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_settings_display,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val display = view.findViewById<View>(R.id.fragment_settings_display)
        display.requestFocus()

        // Sexy BGV
        setupGroupSelection(
            listOf(R.id.sexy_bgv_off, R.id.sexy_bgv_on),
            R.id.sexy_bgv_on
        )

        // Selected Song
        setupGroupSelection(
            listOf(R.id.selected_song_off, R.id.selected_song_on),
            R.id.selected_song_on
        )

        // New Song
        setupGroupSelection(
            listOf(R.id.new_song_off, R.id.new_song_on),
            R.id.new_song_on
        )

        // Logo
        setupGroupSelection(
            listOf(R.id.logo_off, R.id.logo_on),
            R.id.logo_on
        )

        // PK Logo
        setupGroupSelection(
            listOf(R.id.pk_logo_off, R.id.pk_logo_on),
            R.id.pk_logo_on
        )

        // Greetings
        setupGroupSelection(
            listOf(R.id.greetings_off, R.id.greetings_on),
            R.id.greetings_off
        )

        // Features
        setupGroupSelection(
            listOf(R.id.features_off, R.id.features_on),
            R.id.features_off
        )
    }

    private fun setupGroupSelection(viewIds: List<Int>, defaultId: Int) {
        val views = viewIds.mapNotNull { view?.findViewById<View>(it) }

        views.forEach { v ->
            v.setOnClickListener {
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