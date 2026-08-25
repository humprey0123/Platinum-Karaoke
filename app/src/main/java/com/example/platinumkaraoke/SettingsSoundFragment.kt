package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
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

        val touchHandler = View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
                return@OnTouchListener true
            }
            false
        }

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
        introOff.setOnTouchListener(touchHandler)

        introLow.setOnClickListener {
            setSelectedIntroSound(introLow)
        }
        introLow.setOnTouchListener(touchHandler)

        introMiddle.setOnClickListener {
            setSelectedIntroSound(introMiddle)
        }
        introMiddle.setOnTouchListener(touchHandler)

        introHigh.setOnClickListener {
            setSelectedIntroSound(introHigh)
        }
        introHigh.setOnTouchListener(touchHandler)

//        Sound
        soundOff.setOnClickListener {
            setSelectedButtonSound(soundOff)
        }
        soundOff.setOnTouchListener(touchHandler)

        soundLow.setOnClickListener {
            setSelectedButtonSound(soundLow)
        }
        soundLow.setOnTouchListener(touchHandler)

        soundMiddle.setOnClickListener {
            setSelectedButtonSound(soundMiddle)
        }
        soundMiddle.setOnTouchListener(touchHandler)

        soundHigh.setOnClickListener {
            setSelectedButtonSound(soundHigh)
        }
        soundHigh.setOnTouchListener(touchHandler)

        cheerOff.setOnClickListener {
            setSelectedCheerSound(cheerOff)
        }
        cheerOff.setOnTouchListener(touchHandler)

        cheerLow.setOnClickListener {
            setSelectedCheerSound(cheerLow)
        }
        cheerLow.setOnTouchListener(touchHandler)

        cheerMiddle.setOnClickListener {
            setSelectedCheerSound(cheerMiddle)
        }
        cheerMiddle.setOnTouchListener(touchHandler)

        cheerHigh.setOnClickListener {
            setSelectedCheerSound(cheerHigh)
        }
        cheerHigh.setOnTouchListener(touchHandler)

        vocalOff.setOnClickListener {
            setSelectedVocalSound(vocalOff)
        }
        vocalOff.setOnTouchListener(touchHandler)

        vocalLow.setOnClickListener {
            setSelectedVocalSound(vocalLow)
        }
        vocalLow.setOnTouchListener(touchHandler)

        vocalMiddle.setOnClickListener {
            setSelectedVocalSound(vocalMiddle)
        }
        vocalMiddle.setOnTouchListener(touchHandler)

        vocalHigh.setOnClickListener {
            setSelectedVocalSound(vocalHigh)
        }
        vocalHigh.setOnTouchListener(touchHandler)

        applauseOff.setOnClickListener {
            setSelectedApplauseSound(applauseOff)
        }
        applauseOff.setOnTouchListener(touchHandler)

        applauseLow.setOnClickListener {
            setSelectedApplauseSound(applauseLow)
        }
        applauseLow.setOnTouchListener(touchHandler)

        applauseMiddle.setOnClickListener {
            setSelectedApplauseSound(applauseMiddle)
        }
        applauseMiddle.setOnTouchListener(touchHandler)

        applauseHigh.setOnClickListener {
            setSelectedApplauseSound(applauseHigh)
        }
        applauseHigh.setOnTouchListener(touchHandler)


        bgmOff.setOnClickListener {
            setSelectedBGM(bgmOff)
        }
        bgmOff.setOnTouchListener(touchHandler)

        bgmLow.setOnClickListener {
            setSelectedBGM(bgmLow)
        }
        bgmLow.setOnTouchListener(touchHandler)

        bgmMiddle.setOnClickListener {
            setSelectedBGM(bgmMiddle)
        }
        bgmMiddle.setOnTouchListener(touchHandler)

        bgmHigh.setOnClickListener {
            setSelectedBGM(bgmHigh)
        }
        bgmHigh.setOnTouchListener(touchHandler)

        bgmVolumeOff.setOnClickListener {
            setSelectedBGMVolume(bgmVolumeOff)
        }
        bgmVolumeOff.setOnTouchListener(touchHandler)

        bgmVolumeLow.setOnClickListener {
            setSelectedBGMVolume(bgmVolumeLow)
        }
        bgmVolumeLow.setOnTouchListener(touchHandler)

        bgmVolumeMiddle.setOnClickListener {
            setSelectedBGMVolume(bgmVolumeMiddle)
        }
        bgmVolumeMiddle.setOnTouchListener(touchHandler)

        bgmVolumeHigh.setOnClickListener {
            setSelectedBGMVolume(bgmVolumeHigh)
        }
        bgmVolumeHigh.setOnTouchListener(touchHandler)

        drumOff.setOnClickListener {
            setSelectedDrum(drumOff)
        }
        drumOff.setOnTouchListener(touchHandler)

        drumLow.setOnClickListener {
            setSelectedDrum(drumLow)
        }
        drumLow.setOnTouchListener(touchHandler)

        drumMiddle.setOnClickListener {
            setSelectedDrum(drumMiddle)
        }
        drumMiddle.setOnTouchListener(touchHandler)

        drumHigh.setOnClickListener {
            setSelectedDrum(drumHigh)
        }
        drumHigh.setOnTouchListener(touchHandler)


        navVolOff.setOnClickListener {
            setSelectedNavVol(navVolOff)
        }
        navVolOff.setOnTouchListener(touchHandler)

        navVolLow.setOnClickListener {
            setSelectedNavVol(navVolLow)
        }
        navVolLow.setOnTouchListener(touchHandler)

        navVolMiddle.setOnClickListener {
            setSelectedNavVol(navVolMiddle)
        }
        navVolMiddle.setOnTouchListener(touchHandler)

        navVolHigh.setOnClickListener {
            setSelectedNavVol(navVolHigh)
        }
        navVolHigh.setOnTouchListener(touchHandler)



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
