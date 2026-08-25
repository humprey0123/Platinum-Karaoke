package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import android.widget.TextView
import android.widget.Toast

class SettingsDefaultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_settings_default,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clearPopularList = view.findViewById<TextView>(R.id.default_clear_popular_list)
        val factoryReset = view.findViewById<TextView>(R.id.default_factory_reset)
        val clearCoin = view.findViewById<TextView>(R.id.default_clear_coin)
        val clearProsco = view.findViewById<TextView>(R.id.default_clear_prosco)
        val countryEnumeration = view.findViewById<TextView>(R.id.default_country_enumeration)
        val enableHomeMenu = view.findViewById<TextView>(R.id.default_enable_home_menu_on_boot)
        val defaultFragment = view.findViewById<View>(R.id.fragment_settings_default)

        defaultFragment.requestFocus()

        val touchHandler = View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
                return@OnTouchListener true
            }
            false
        }

        val settingsItems = listOf(
            clearPopularList,
            factoryReset,
            clearCoin,
            clearProsco,
            countryEnumeration,
            enableHomeMenu
        )

        settingsItems.forEach { item ->
            item.setOnClickListener {
                // Clear selection from all items
                settingsItems.forEach { it.isSelected = false }
                // Set the clicked item as selected
                item.isSelected = true
            }
            item.setOnTouchListener(touchHandler)
        }

        // Optionally set the first item as selected by default
        clearPopularList.isSelected = true
    }
}