package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SettingsMicFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_settings_mic,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val micFragment = view.findViewById<View>(R.id.fragment_settings_mic_settings)
        micFragment.requestFocus()

        // Song
        setupGroupSelection(
            listOf(R.id.song_usb, R.id.song_network),
            R.id.song_usb
        )

        // System Upgrade
        setupGroupSelection(
            listOf(R.id.system_upgrade_usb, R.id.system_upgrade_network),
            R.id.system_upgrade_usb
        )

        // Auto OTA
        setupGroupSelection(
            listOf(R.id.auto_ota_disable, R.id.auto_ota_Enable),
            R.id.auto_ota_disable
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