package com.example.platinumkaraoke

import android.content.Context
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.SeekBar
import android.widget.TextView
import android.widget.RadioButton

class SettingsPopup(private val context: Context) {

    fun show(anchor: View) {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.popup_settings, null)

        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val popupWidth = (screenWidth * 0.3).toInt()

        val popup = PopupWindow(
            view,
            popupWidth,
            WindowManager.LayoutParams.WRAP_CONTENT,
            true
        )

        popup.elevation = 20f

        popup.showAtLocation(
            anchor.rootView,
            Gravity.TOP or Gravity.END,
            0,
            0
        )

        setupRadioGroup(view)

        setupSliders(view)
    }

    private fun setupSliders(view: View) {
        val sliders = listOf(
            "Music Volume",
            "Key",
            "Tempo",
            "Music Level",
            "Mic Echo"
        )

        val container = view.findViewById<LinearLayout>(R.id.popup_settings_seeker)

        sliders.forEach { text ->
            val item = LayoutInflater.from(context)
                .inflate(R.layout.item_setting_slider, container, false)

            setupSlider(item, text)
            container.addView(item)
        }
    }

    private fun setupSlider(view: View, text: String) {
        val label = view.findViewById<TextView>(R.id.label)
        val seek = view.findViewById<SeekBar>(R.id.seek)
        val container = view.findViewById<View>(R.id.layout_container)
        val level = view.findViewById<TextView>(R.id.popup_settings_level)

        label.text = text

        // 👇 Show level only for specific sliders
        val showLevel = text == "Tempo" || text == "Music Level" || text == "Mic Echo"
        level.visibility = if (showLevel) View.VISIBLE else View.GONE

        // 👇 Update value when sliding
        if (showLevel) {
            level.text = seek.progress.toString()

            seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    level.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })
        }

        // Focus highlight (your existing logic)
        seek.setOnFocusChangeListener { _, hasFocus ->
            container.isActivated = hasFocus
        }
    }

    // Radio Group
    private fun setupRadioGroup(view: View) {

        val parent = view.findViewById<View>(R.id.bgv_row_container)

        val radioButtons = listOf(
            view.findViewById<RadioButton>(R.id.bgv_auto),
            view.findViewById<RadioButton>(R.id.bgv_3d),
            view.findViewById<RadioButton>(R.id.bgv_view),
            view.findViewById<RadioButton>(R.id.bgv_event),
            view.findViewById<RadioButton>(R.id.bgv_abstract),
            view.findViewById<RadioButton>(R.id.bgv_mv),
            view.findViewById<RadioButton>(R.id.bgv_sexy),
            view.findViewById<RadioButton>(R.id.bgv_user)
        )

        radioButtons.forEach { radio ->

            // selection logic
            radio.setOnClickListener {
                radioButtons.forEach { it.isChecked = false }
                radio.isChecked = true
            }

            // 👇 THIS is the key part
            radio.setOnFocusChangeListener { _, hasFocus ->
                parent.isActivated = hasFocus
            }
        }
    }
}