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

        main.requestFocus()

        navAuthentication.setOnClickListener {
            replaceSettingsContent(SettingsAuthenticationFragment())
        }

        navSound.setOnClickListener {
            replaceSettingsContent(SettingsSoundFragment())
        }

//        navEqualizer.setOnClickListener {
//            replaceSettingsContent(EqualizerFragment())
//        }

        // Show Authentication by default
        replaceSettingsContent(SettingsAuthenticationFragment())
    }

    private fun replaceSettingsContent(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.settings_content, fragment)
            .commit()
    }
}