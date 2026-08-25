package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SettingsLyricFontFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_settings_lyric_font,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val modeDefault = view.findViewById<TextView>(R.id.lyric_font_default)
        val modeRandomAll = view.findViewById<TextView>(R.id.lyric_font_random_all)
        val modeSelectOne = view.findViewById<TextView>(R.id.lyric_font_select_one)
        val selectedText = view.findViewById<TextView>(R.id.lyric_font_selected)

        val group1 = view.findViewById<RadioGroup>(R.id.lyric_font_group1)
        val group2 = view.findViewById<RadioGroup>(R.id.lyric_font_group2)
        val lyricFontSample = view.findViewById<StrokeTextView>(R.id.lyric_font_sample)

        // Setup Stroke for the sample text
        lyricFontSample.strokeColor = android.graphics.Color.WHITE
        lyricFontSample.strokeWidth = 8f // Clear white outline

        // Mode Selection logic
        val modeViews = listOf(modeDefault, modeRandomAll, modeSelectOne)
        val selectMode = { selectedView: TextView, text: String ->
            modeViews.forEach { it.isSelected = (it == selectedView) }
            selectedText.text = text
            selectedView.requestFocus()
        }

        // Helper to handle RadioButton clicks across both groups
        val onFontSelected = { radioButton: RadioButton, otherGroup: RadioGroup ->
            otherGroup.clearCheck()
            // Note: In a real implementation, you would also update the typeface of lyricFontSample here
            // e.g., lyricFontSample.typeface = radioButton.typeface
        }

        val fontButtonsGroup1 = listOf(
            R.id.lyric_font_agenta_chubby_demo, R.id.lyric_font_albha, R.id.lyric_font_black_ops_one_regular,
            R.id.lyric_font_machiato_show, R.id.lyric_font_merienda_regular
        )
        val fontButtonsGroup2 = listOf(
            R.id.lyric_font_merriweather_bold, R.id.lyric_font_monoline_script_regular, R.id.lyric_font_sweet_candy,
            R.id.lyric_font_ubuntu_bold, R.id.lyric_font_roboto_medium
        )

        fontButtonsGroup1.forEach { id ->
            val rb = view.findViewById<RadioButton>(id)
            rb.setOnClickListener { onFontSelected(rb, group2) }
        }

        fontButtonsGroup2.forEach { id ->
            val rb = view.findViewById<RadioButton>(id)
            rb.setOnClickListener { onFontSelected(rb, group1) }
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
        view.findViewById<RadioButton>(R.id.lyric_font_agenta_chubby_demo).isChecked = true
        selectMode(modeSelectOne, "Select One")
    }
}
