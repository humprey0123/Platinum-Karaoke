package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
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

        main.requestFocus()

        navAuthentication.setOnClickListener {
            setSelectedNav(navAuthentication)
            replaceSettingsContent(SettingsAuthenticationFragment())
        }

        navSound.setOnClickListener {
            setSelectedNav(navSound)
            replaceSettingsContent(SettingsSoundFragment())
        }

        navEqualizer.setOnClickListener {
            setSelectedNav(navEqualizer)
            replaceSettingsContent(SettingsEqualizerFragment())
        }

        navScore.setOnClickListener {
            setSelectedNav(navScore)
            replaceSettingsContent(SettingsScoreFragment())
        }


        navCoinTime.setOnClickListener {
            setSelectedNav(navCoinTime)
            replaceSettingsContent(SettingsCoinTimeFragment())
        }

        navDisplay.setOnClickListener {
            setSelectedNav(navDisplay)
            replaceSettingsContent(SettingsDisplayFragment())
        }

        navUserContent.setOnClickListener {
            setSelectedNav(navUserContent)
            replaceSettingsContent(SettingsUserContentFragment())
        }

        navGreetings.setOnClickListener {
            setSelectedNav(navGreetings)
            replaceSettingsContent(SettingsGreetingsFragment())
        }

        navDefault.setOnClickListener {
            setSelectedNav(navDefault)
            replaceSettingsContent(SettingsDefaultFragment())
        }
        navPassword.setOnClickListener {
            setSelectedNav(navPassword)
            replaceSettingsContent(SettingsPasswordFragment())
        }
        navUpdate.setOnClickListener {
            setSelectedNav(navUpdate)
            replaceSettingsContent(SettingsUpdateFragment())
        }
        navMic.setOnClickListener {
            setSelectedNav(navMic)
            replaceSettingsContent(SettingsMicFragment())
        }
        navLyricFont.setOnClickListener {
            setSelectedNav(navLyricFont)
            replaceSettingsContent(SettingsLyricFontFragment())
        }
        navLyricColor.setOnClickListener {
            setSelectedNav(navLyricColor)
            replaceSettingsContent(SettingsLyricColorFragment())
        }
        navPlatinumLink.setOnClickListener {
            setSelectedNav(navPlatinumLink)
            replaceSettingsContent(SettingsPlatinumLinkFragment())
        }

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