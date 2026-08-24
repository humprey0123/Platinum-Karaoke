package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SettingsLyricColorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_settings_lyric_color,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val modeDefault = view.findViewById<TextView>(R.id.lyric_color_default)
        val modeRandomAll = view.findViewById<TextView>(R.id.lyric_color_random_all)
        val modeSelectOne = view.findViewById<TextView>(R.id.lyric_color_select_one)
        val selectedText = view.findViewById<TextView>(R.id.lyric_color_selected)

        val group1 = view.findViewById<RadioGroup>(R.id.lyric_color_group1)
        val group2 = view.findViewById<RadioGroup>(R.id.lyric_color_group2)
        val lyricColorSample = view.findViewById<StrokeTextView>(R.id.lyric_color_sample)

        // Setup Stroke for the sample text
        lyricColorSample.strokeColor = android.graphics.Color.WHITE
        lyricColorSample.strokeWidth = 8f // Clear white outline

        // Mode Selection logic
        val modeViews = listOf(modeDefault, modeRandomAll, modeSelectOne)
        val selectMode = { selectedView: TextView, text: String ->
            modeViews.forEach { it.isSelected = (it == selectedView) }
            selectedText.text = text
            selectedView.requestFocus()
        }

        // Helper to handle RadioButton clicks across both groups
        val onColorSelected = { radioButton: RadioButton, otherGroup: RadioGroup ->
            otherGroup.clearCheck()
            selectMode(modeSelectOne, radioButton.text.toString())
            // Update sample text color based on selection if needed
            // For now, we update the "Selected" text which is already done in selectMode
        }

        val colorButtonsGroup1 = listOf(
            R.id.lyric_color_navy_black, R.id.lyric_color_sky_navy, R.id.lyric_color_rose_wine,
            R.id.lyric_color_olive_green, R.id.lyric_color_cherry_gold
        )
        val colorButtonsGroup2 = listOf(
            R.id.lyric_color_mustard_brown, R.id.lyric_color_emerald_mint, R.id.lyric_color_mocha_red,
            R.id.lyric_color_cobalt_navy, R.id.lyric_color_scarlet_gold
        )

        colorButtonsGroup1.forEach { id ->
            val rb = view.findViewById<RadioButton>(id)
            rb.setOnClickListener { onColorSelected(rb, group2) }
        }

        colorButtonsGroup2.forEach { id ->
            val rb = view.findViewById<RadioButton>(id)
            rb.setOnClickListener { onColorSelected(rb, group1) }
        }

        modeDefault.setOnClickListener {
            selectMode(modeDefault, "Default")
            group1.clearCheck()
            group2.clearCheck()
        }
        modeRandomAll.setOnClickListener {
            selectMode(modeRandomAll, "Random All")
            group1.clearCheck()
            group2.clearCheck()
        }
        modeSelectOne.setOnClickListener {
            selectMode(modeSelectOne, "Select One")
        }

        // Set default states
        view.findViewById<RadioButton>(R.id.lyric_color_navy_black).isChecked = true
        selectMode(modeSelectOne, "Navy Black")
    }
}
