package com.example.platinumkaraoke

import android.content.Context
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.SeekBar
import android.widget.TextView
import android.widget.RadioButton

class SettingsPopup(private val context: Context) {
    private val prefs = context.getSharedPreferences("karaoke_settings", Context.MODE_PRIVATE)

    private var popup: PopupWindow? = null

    fun dismiss() {
        popup?.dismiss()
        popup = null
    }

    fun show(anchor: View) {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.popup_settings, null)

        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val popupWidth = (screenWidth * 0.3).toInt()

        popup = PopupWindow(
            view,
            popupWidth,
            WindowManager.LayoutParams.WRAP_CONTENT,
            true
        )

        val btnSettings = view.findViewById<View>(R.id.btn_settings)

        val touchHandler = View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
                return@OnTouchListener true
            }
            false
        }

        btnSettings.setOnTouchListener(touchHandler)
        btnSettings.setOnClickListener {
            (context as MainActivity).showSettings(it)
        }

        popup?.elevation = 20f

        popup?.showAtLocation(
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

        val key = text.replace(" ", "_").lowercase()

        // ✅ Load saved value (default = 50)
        val savedValue = prefs.getInt(key, 5)
        seek.progress = savedValue

        val showLevel = text == "Tempo" || text == "Music Level" || text == "Mic Echo"
        level.visibility = if (showLevel) View.VISIBLE else View.GONE

        if (showLevel) {
            level.text = savedValue.toString()
        }

        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                // ✅ Update UI
                if (showLevel) {
                    level.text = progress.toString()
                }

                // ✅ SAVE
                prefs.edit().putInt(key, progress).apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

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

        // ✅ Load saved ID
        val savedId = prefs.getInt("bgv_mode", R.id.bgv_auto)

        radioButtons.forEach { radio ->
            radio.isChecked = radio.id == savedId
        }

        radioButtons.forEach { radio ->

            val touchHandler = View.OnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    v.performClick()
                }
                false
            }

            radio.setOnTouchListener(touchHandler)
            radio.setOnClickListener {
                radioButtons.forEach { it.isChecked = false }
                radio.isChecked = true

                // ✅ SAVE ID directly
                prefs.edit().putInt("bgv_mode", radio.id).apply()
            }

            radio.setOnFocusChangeListener { _, hasFocus ->
                parent.isActivated = hasFocus
            }
        }
    }
}