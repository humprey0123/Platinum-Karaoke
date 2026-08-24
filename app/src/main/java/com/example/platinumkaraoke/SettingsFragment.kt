package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navAuthentication = view.findViewById<View>(R.id.nav_authentication)
        val main = view.findViewById<View>(R.id.fragment_settings)
        val navSound = view.findViewById<View>(R.id.nav_sound)
        val navEqualizer = view.findViewById<View>(R.id.nav_equalizer)
        val navScore = view.findViewById<View>(R.id.nav_score)
        val navCoinTime = view.findViewById<View>(R.id.nav_coin_time)
        val navDisplay = view.findViewById<View>(R.id.nav_display)
        val navUserContent = view.findViewById<View>(R.id.nav_user_content)
        val navGreetings = view.findViewById<View>(R.id.nav_greetings)
        val navDefault = view.findViewById<View>(R.id.nav_default)
        val navPassword = view.findViewById<View>(R.id.nav_password)
        val navUpdate = view.findViewById<View>(R.id.nav_update)
        val navMic = view.findViewById<View>(R.id.nav_mic_settings)
        val navLyricFont = view.findViewById<View>(R.id.nav_lyric_font)
        val navLyricColor = view.findViewById<View>(R.id.nav_lyric_color)
        val navPlatinumLink = view.findViewById<View>(R.id.nav_platinum_link)

        val touchHandler = View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }
            false
        }

        main.requestFocus()

        navAuthentication.setOnClickListener {
            setSelectedNav(navAuthentication)
            replaceSettingsContent(SettingsAuthenticationFragment())
        }

        navAuthentication.setOnTouchListener(touchHandler)

        navSound.setOnClickListener {
            setSelectedNav(navSound)
            replaceSettingsContent(SettingsSoundFragment())
        }

        navSound.setOnTouchListener(touchHandler)

        navEqualizer.setOnClickListener {
            setSelectedNav(navEqualizer)
            replaceSettingsContent(SettingsEqualizerFragment())
        }
        navEqualizer.setOnTouchListener(touchHandler)

        navScore.setOnClickListener {
            setSelectedNav(navScore)
            replaceSettingsContent(SettingsScoreFragment())
        }
        navScore.setOnTouchListener(touchHandler)


        navCoinTime.setOnClickListener {
            setSelectedNav(navCoinTime)
            replaceSettingsContent(SettingsCoinTimeFragment())
        }
        navCoinTime.setOnTouchListener(touchHandler)

        navDisplay.setOnClickListener {
            setSelectedNav(navDisplay)
            replaceSettingsContent(SettingsDisplayFragment())
        }
        navDisplay.setOnTouchListener(touchHandler)

        navUserContent.setOnClickListener {
            setSelectedNav(navUserContent)
            replaceSettingsContent(SettingsUserContentFragment())
        }
        navUserContent.setOnTouchListener(touchHandler)

        navGreetings.setOnClickListener {
            setSelectedNav(navGreetings)
            replaceSettingsContent(SettingsGreetingsFragment())
        }
        navGreetings.setOnTouchListener(touchHandler)

        navDefault.setOnClickListener {
            setSelectedNav(navDefault)
            replaceSettingsContent(SettingsDefaultFragment())
        }
        navDefault.setOnTouchListener(touchHandler)

        navPassword.setOnClickListener {
            setSelectedNav(navPassword)
            replaceSettingsContent(SettingsPasswordFragment())
        }
        navPassword.setOnTouchListener(touchHandler)

        navUpdate.setOnClickListener {
            setSelectedNav(navUpdate)
            replaceSettingsContent(SettingsUpdateFragment())
        }
        navUpdate.setOnTouchListener(touchHandler)

        navMic.setOnClickListener {
            setSelectedNav(navMic)
            replaceSettingsContent(SettingsMicFragment())
        }
        navMic.setOnTouchListener(touchHandler)

        navLyricFont.setOnClickListener {
            setSelectedNav(navLyricFont)
            replaceSettingsContent(SettingsLyricFontFragment())
        }
        navLyricFont.setOnTouchListener(touchHandler)

        navLyricColor.setOnClickListener {
            setSelectedNav(navLyricColor)
            replaceSettingsContent(SettingsLyricColorFragment())
        }
        navLyricColor.setOnTouchListener(touchHandler)

        navPlatinumLink.setOnClickListener {
            setSelectedNav(navPlatinumLink)
            replaceSettingsContent(SettingsPlatinumLinkFragment())
        }
        navPlatinumLink.setOnTouchListener(touchHandler)


        // Show Authentication by default
        setSelectedNav(navAuthentication)
        replaceSettingsContent(SettingsAuthenticationFragment())
    }

    private fun setSelectedNav(selectedView: View) {
        selectedView.isSelected = true

        val navViews = listOf(
            view?.findViewById<View>(R.id.nav_authentication),
            view?.findViewById<View>(R.id.nav_sound),
            view?.findViewById<View>(R.id.nav_equalizer),
            view?.findViewById<View>(R.id.nav_score) ,
            view?.findViewById<View>(R.id.nav_coin_time),
            view?.findViewById<View>(R.id.nav_display),
            view?.findViewById<View>(R.id.nav_user_content),
            view?.findViewById<View>(R.id.nav_greetings),
            view?.findViewById<View>(R.id.nav_default),
            view?.findViewById<View>(R.id.nav_password),
            view?.findViewById<View>(R.id.nav_update),
            view?.findViewById<View>(R.id.nav_mic_settings),
            view?.findViewById<View>(R.id.nav_lyric_font),
            view?.findViewById<View>(R.id.nav_lyric_color),
            view?.findViewById<View>(R.id.nav_platinum_link),
        )
        navViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }

    private fun replaceSettingsContent(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.settings_content, fragment)
            .commit()
    }
}