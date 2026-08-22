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
        val navyBlack = view.findViewById<RadioButton>(R.id.lyric_color_navy_black)
        val lyricColorSample = view.findViewById<StrokeTextView>(R.id.lyric_color_sample)

        // Setup Stroke for the sample text
        lyricColorSample.strokeColor = android.graphics.Color.WHITE
        lyricColorSample.strokeWidth = 8f // Clear white outline

        // Synchronization between two RadioGroups
        group1.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                group2.clearCheck()
            }
        }
        group2.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                group1.clearCheck()
            }
        }

        // Mode Selection logic
        val modeViews = listOf(modeDefault, modeRandomAll, modeSelectOne)
        val selectMode = { selectedView: TextView, text: String ->
            modeViews.forEach { it.isSelected = (it == selectedView) }
            selectedText.text = text
            selectedView.requestFocus()
        }

        modeDefault.setOnClickListener {
            selectMode(modeDefault, "Default")
        }
        modeRandomAll.setOnClickListener {
            selectMode(modeRandomAll, "Random All")
        }
        modeSelectOne.setOnClickListener {
            selectMode(modeSelectOne, "Select One")
        }

        // Set default states
        navyBlack.isChecked = true
        selectMode(modeSelectOne, "Select One")
    }
}
