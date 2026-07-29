package com.example.platinumkaraoke

import android.content.Context
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.SeekBar
import android.widget.TextView

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

        label.text = text

        seek.setOnFocusChangeListener { _, hasFocus ->
            container.isActivated = hasFocus
        }
    }
}