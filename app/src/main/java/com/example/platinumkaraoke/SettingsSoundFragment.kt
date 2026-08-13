package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SettingsSoundFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_settings_sound,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val main = view.findViewById<View>(R.id.fragment_settings_sound)
        main.requestFocus()

        val introOff = view.findViewById<View>(R.id.sound_intro_off)
        val introLow = view.findViewById<View>(R.id.sound_intro_low)
        val introMiddle = view.findViewById<View>(R.id.sound_intro_middle)
        val introHigh = view.findViewById<View>(R.id.sound_intro_high)

        val soundOff = view.findViewById<View>(R.id.sound_button_off)
        val soundLow = view.findViewById<View>(R.id.sound_button_low)
        val soundMiddle = view.findViewById<View>(R.id.sound_button_middle)
        val soundHigh = view.findViewById<View>(R.id.sound_button_high)

        val cheerOff = view.findViewById<View>(R.id.sound_cheer_off)
        val cheerLow = view.findViewById<View>(R.id.sound_cheer_low)
        val cheerMiddle = view.findViewById<View>(R.id.sound_cheer_middle)
        val cheerHigh = view.findViewById<View>(R.id.sound_cheer_high)

        val vocalOff = view.findViewById<View>(R.id.sound_vocal_off)
        val vocalLow = view.findViewById<View>(R.id.sound_vocal_low)
        val vocalMiddle = view.findViewById<View>(R.id.sound_vocal_middle)
        val vocalHigh = view.findViewById<View>(R.id.sound_vocal_high)

        val applauseOff = view.findViewById<View>(R.id.sound_applause_off)
        val applauseLow = view.findViewById<View>(R.id.sound_applause_low)
        val applauseMiddle = view.findViewById<View>(R.id.sound_applause_middle)
        val applauseHigh = view.findViewById<View>(R.id.sound_applause_high)

        val bgmOff = view.findViewById<View>(R.id.sound_bgm_off)
        val bgmLow = view.findViewById<View>(R.id.sound_bgm_low)
        val bgmMiddle = view.findViewById<View>(R.id.sound_bgm_middle)
        val bgmHigh = view.findViewById<View>(R.id.sound_bgm_high)

        val bgmVolumeOff = view.findViewById<View>(R.id.sound_bgm_volume_off)
        val bgmVolumeLow = view.findViewById<View>(R.id.sound_bgm_volume_low)
        val bgmVolumeMiddle = view.findViewById<View>(R.id.sound_bgm_volume_middle)
        val bgmVolumeHigh = view.findViewById<View>(R.id.sound_bgm_volume_high)

        val drumOff = view.findViewById<View>(R.id.sound_drum_off)
        val drumLow = view.findViewById<View>(R.id.sound_drum_low)
        val drumMiddle = view.findViewById<View>(R.id.sound_drum_middle)
        val drumHigh = view.findViewById<View>(R.id.sound_drum_high)

        val navVolOff = view.findViewById<View>(R.id.sound_nav_vol_off)
        val navVolLow = view.findViewById<View>(R.id.sound_nav_vol_low)
        val navVolMiddle = view.findViewById<View>(R.id.sound_nav_vol_medium)
        val navVolHigh = view.findViewById<View>(R.id.sound_nav_vol_high)

//        Intro
        introOff.setOnClickListener {
            setSelectedIntroSound(introOff)
        }
        introLow.setOnClickListener {
            setSelectedIntroSound(introLow)
        }
        introMiddle.setOnClickListener {
            setSelectedIntroSound(introMiddle)
        }
        introHigh.setOnClickListener {
            setSelectedIntroSound(introHigh)
        }

//        Sound
        soundOff.setOnClickListener {
            setSelectedButtonSound(soundOff)
        }
        soundLow.setOnClickListener {
            setSelectedButtonSound(soundLow)
        }
        soundMiddle.setOnClickListener {
            setSelectedButtonSound(soundMiddle)
        }
        soundHigh.setOnClickListener {
            setSelectedButtonSound(soundHigh)
        }

        cheerOff.setOnClickListener {
            setSelectedCheerSound(cheerOff)
        }
        cheerLow.setOnClickListener {
            setSelectedCheerSound(cheerLow)
        }
        cheerMiddle.setOnClickListener {
            setSelectedCheerSound(cheerMiddle)
        }
        cheerHigh.setOnClickListener {
            setSelectedCheerSound(cheerHigh)
        }

        vocalOff.setOnClickListener {
            setSelectedVocalSound(vocalOff)
        }
        vocalLow.setOnClickListener {
            setSelectedVocalSound(vocalLow)
        }
        vocalMiddle.setOnClickListener {
            setSelectedVocalSound(vocalMiddle)
        }
        vocalHigh.setOnClickListener {
            setSelectedVocalSound(vocalHigh)
        }

        applauseOff.setOnClickListener {
            setSelectedApplauseSound(applauseOff)
        }
        applauseLow.setOnClickListener {
            setSelectedApplauseSound(applauseLow)
        }
        applauseMiddle.setOnClickListener {
            setSelectedApplauseSound(applauseMiddle)
        }
        applauseHigh.setOnClickListener {
            setSelectedApplauseSound(applauseHigh)
        }


        bgmOff.setOnClickListener {
            setSelectedBGM(bgmOff)
        }
        bgmLow.setOnClickListener {
            setSelectedBGM(bgmLow)
        }
        bgmMiddle.setOnClickListener {
            setSelectedBGM(bgmMiddle)
        }
        bgmHigh.setOnClickListener {
            setSelectedBGM(bgmHigh)
        }

        bgmVolumeOff.setOnClickListener {
            setSelectedBGMVolume(bgmVolumeOff)
        }
        bgmVolumeLow.setOnClickListener {
            setSelectedBGMVolume(bgmVolumeLow)
        }
        bgmVolumeMiddle.setOnClickListener {
            setSelectedBGMVolume(bgmVolumeMiddle)
        }
        bgmVolumeHigh.setOnClickListener {
            setSelectedBGMVolume(bgmVolumeHigh)
        }

        drumOff.setOnClickListener {
            setSelectedDrum(drumOff)
        }
        drumLow.setOnClickListener {
            setSelectedDrum(drumLow)
        }
        drumMiddle.setOnClickListener {
            setSelectedDrum(drumMiddle)
        }
        drumHigh.setOnClickListener {
            setSelectedDrum(drumHigh)
        }


        navVolOff.setOnClickListener {
            setSelectedNavVol(navVolOff)
        }
        navVolLow.setOnClickListener {
            setSelectedNavVol(navVolLow)
        }
        navVolMiddle.setOnClickListener {
            setSelectedNavVol(navVolMiddle)
        }
        navVolHigh.setOnClickListener {
            setSelectedNavVol(navVolHigh)
        }



        // Default button
        setSelectedIntroSound(introLow)
        setSelectedButtonSound(soundMiddle)
        setSelectedCheerSound(cheerLow)
        setSelectedVocalSound(vocalOff)
        setSelectedApplauseSound(applauseHigh)
        setSelectedBGM(bgmMiddle)
        setSelectedBGMVolume(bgmVolumeLow)
        setSelectedDrum(drumMiddle)
        setSelectedNavVol(navVolMiddle)


    }


    // Selector
    private fun setSelectedIntroSound(selectedView: View) {
        selectedView.isSelected = true

        val introSoundViews = listOf(
            view?.findViewById<View>(R.id.sound_intro_off),
            view?.findViewById<View>(R.id.sound_intro_low),
            view?.findViewById<View>(R.id.sound_intro_middle),
            view?.findViewById<View>(R.id.sound_intro_high),
        )

        introSoundViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }

    private fun setSelectedButtonSound(selectedView: View) {
        selectedView.isSelected = true

        val introSoundViews = listOf(
            view?.findViewById<View>(R.id.sound_button_off),
            view?.findViewById<View>(R.id.sound_button_low),
            view?.findViewById<View>(R.id.sound_button_middle),
            view?.findViewById<View>(R.id.sound_button_high),
        )

        introSoundViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }

    private fun setSelectedCheerSound(selectedView: View) {
        selectedView.isSelected = true

        val introSoundViews = listOf(
            view?.findViewById<View>(R.id.sound_cheer_off),
            view?.findViewById<View>(R.id.sound_cheer_low),
            view?.findViewById<View>(R.id.sound_cheer_middle),
            view?.findViewById<View>(R.id.sound_cheer_high),
        )

        introSoundViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }

    private fun setSelectedVocalSound(selectedView: View) {
        selectedView.isSelected = true

        val introSoundViews = listOf(
            view?.findViewById<View>(R.id.sound_vocal_off),
            view?.findViewById<View>(R.id.sound_vocal_low),
            view?.findViewById<View>(R.id.sound_vocal_middle),
            view?.findViewById<View>(R.id.sound_vocal_high),
        )

        introSoundViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }

    private fun setSelectedApplauseSound(selectedView: View) {
        selectedView.isSelected = true

        val introSoundViews = listOf(
            view?.findViewById<View>(R.id.sound_applause_off),
            view?.findViewById<View>(R.id.sound_applause_low),
            view?.findViewById<View>(R.id.sound_applause_middle),
            view?.findViewById<View>(R.id.sound_applause_high),
        )

        introSoundViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }

    private fun setSelectedBGM(selectedView: View) {
        selectedView.isSelected = true

        val introSoundViews = listOf(
            view?.findViewById<View>(R.id.sound_bgm_off),
            view?.findViewById<View>(R.id.sound_bgm_low),
            view?.findViewById<View>(R.id.sound_bgm_middle),
            view?.findViewById<View>(R.id.sound_bgm_high),
        )

        introSoundViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }

    private fun setSelectedBGMVolume(selectedView: View) {
        selectedView.isSelected = true

        val introSoundViews = listOf(
            view?.findViewById<View>(R.id.sound_bgm_volume_off),
            view?.findViewById<View>(R.id.sound_bgm_volume_low),
            view?.findViewById<View>(R.id.sound_bgm_volume_middle),
            view?.findViewById<View>(R.id.sound_bgm_volume_high),
        )

        introSoundViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }

    private fun setSelectedDrum(selectedView: View) {
        selectedView.isSelected = true

        val introSoundViews = listOf(
            view?.findViewById<View>(R.id.sound_drum_off),
            view?.findViewById<View>(R.id.sound_drum_low),
            view?.findViewById<View>(R.id.sound_drum_middle),
            view?.findViewById<View>(R.id.sound_drum_high),
        )

        introSoundViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }

    private fun setSelectedNavVol(selectedView: View) {
        selectedView.isSelected = true

        val introSoundViews = listOf(
            view?.findViewById<View>(R.id.sound_nav_vol_off),
            view?.findViewById<View>(R.id.sound_nav_vol_low),
            view?.findViewById<View>(R.id.sound_nav_vol_medium),
            view?.findViewById<View>(R.id.sound_nav_vol_high),
        )

        introSoundViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }
}
