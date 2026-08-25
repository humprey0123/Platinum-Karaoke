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

        setupToggle(view, R.id.mic_on, R.id.mic_off, true)
        setupToggle(view, R.id.feedback_cancel_on, R.id.feedback_cancel_off, true)
        setupToggle(view, R.id.user_echo_on, R.id.user_echo_off, true)
        setupToggle(view, R.id.echo_on, R.id.echo_off, true)
    }

    private fun setupToggle(view: View, onId: Int, offId: Int, defaultOn: Boolean) {
        val onBtn = view.findViewById<View>(onId)
        val offBtn = view.findViewById<View>(offId)

        onBtn.isSelected = defaultOn
        offBtn.isSelected = !defaultOn

        onBtn.setOnClickListener {
            onBtn.isSelected = true
            offBtn.isSelected = false
        }

        offBtn.setOnClickListener {
            offBtn.isSelected = true
            onBtn.isSelected = false
        }
    }
}