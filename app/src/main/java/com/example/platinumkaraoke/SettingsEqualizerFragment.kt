package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment

class SettingsEqualizerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_settings_equalizer,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val equalizer = view.findViewById<View>(R.id.fragment_settings_equalizer)
        equalizer.requestFocus()

        setupGroupSelection(
            listOf(
                R.id.equalizer_standard,
                R.id.equalizer_classic,
                R.id.equalizer_jazz,
                R.id.equalizer_rock,
                R.id.equalizer_pop,
                R.id.equalizer_ballad,
                R.id.equalizer_disco,
                R.id.equalizer_user
            ),
            R.id.equalizer_standard
        )
        setupEqualizerBands()
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

    private fun setupEqualizerBands() {
        val bandConfigs = listOf(
            R.id.seekbar_low to R.id.text_gain_low,
            R.id.seekbar_mid_low to R.id.text_gain_mid_low,
            R.id.seekbar_mid_high to R.id.text_gain_mid_high,
            R.id.seekbar_high to R.id.text_gain_high
        )

        bandConfigs.forEach { (seekbarId, textId) ->
            val seekbar = view?.findViewById<SeekBar>(seekbarId)
            val textView = view?.findViewById<TextView>(textId)

            seekbar?.apply {
                max = 20
                setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        updateGainText(textView, progress)
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                })
                // Set initial text based on current progress
                updateGainText(textView, progress)
            }
        }
    }

    private fun updateGainText(textView: TextView?, progress: Int) {
        val gain = progress - 10
        val text = if (gain > 0) "+$gain dB" else "$gain dB"
        textView?.text = text
    }
}