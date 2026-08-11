package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SettingsAuthenticationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_settings_authentication,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val main = view.findViewById<View>(R.id.fragment_settings_authentication)
        val usb = view.findViewById<View>(R.id.usb_auth)
        val phone = view.findViewById<View>(R.id.phone_auth)

        main.requestFocus()

        usb.setOnClickListener {
            setSelectedNav(usb)
        }

        phone.setOnClickListener {
            setSelectedNav(phone)
        }
    }

    private fun setSelectedNav(selectedView: View) {
        selectedView.isSelected = true

        val navViews = listOf(
            view?.findViewById<View>(R.id.usb_auth),
            view?.findViewById<View>(R.id.phone_auth)
        )

        navViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }
}