package com.example.platinumkaraoke

import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import android.widget.PopupWindow
import android.view.LayoutInflater
import android.view.Gravity
import android.view.WindowManager

class MainActivity : FragmentActivity() {

    private lateinit var bg: ImageView
    private lateinit var navHome: View
    private lateinit var navSearch: View

    private lateinit var navSettings: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views once (cleaner + faster)
        bg = findViewById(R.id.bg)
        navHome = findViewById(R.id.nav_home)
        navSearch = findViewById(R.id.nav_search)
        navSettings = findViewById(R.id.nav_settings) // ✅ ADD THIS

        setupNavigation()

        if (savedInstanceState == null) {
            showHome()
        }
    }

    private fun setupNavigation() {
        // Click (touch + remote enter)
        navHome.setOnClickListener { showHome() }
        navSearch.setOnClickListener { showSearch() }

        // 🔥 Fix: make touch behave like single tap (no double tap issue)
        val touchHandler = View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }
            false
        }

        navSettings.setOnClickListener {
            showSettingsPopup(it)
        }
        navHome.setOnTouchListener(touchHandler)
        navSearch.setOnTouchListener(touchHandler)
    }

    fun showHome() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, HomeFragment())
            .commit()

        bg.apply {
            setImageResource(R.drawable.bg_home)
            setColorFilter(0x80000000.toInt())
        }
    }

    fun showSearch(selectedCategory: String? = null) {
        val fragment = SearchFragment().apply {
            arguments = Bundle().apply {
                putString("selected_filter", selectedCategory)
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, fragment)
            .commit()

        bg.setImageResource(R.drawable.bg_songlist)
    }

    // 🔥 Fullscreen (modern + backward compatible)
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(
                WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars()
            )
            window.insetsController?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }

//    Settings Popup

    private fun showSettingsPopup(anchor: View) {
        val view = LayoutInflater.from(this)
            .inflate(R.layout.popup_settings, null)

        val popup = PopupWindow(
            view,
            300,
            WindowManager.LayoutParams.WRAP_CONTENT,
            true
        )

        popup.elevation = 20f

        // Show at TOP RIGHT of the icon
        popup.showAtLocation(window.decorView, Gravity.TOP or Gravity.END, 0, 0)

        // Optional: handle clicks
        view.findViewById<View>(R.id.btn_profile).setOnClickListener {
            popup.dismiss()
            // TODO: open profile
        }

        view.findViewById<View>(R.id.btn_logout).setOnClickListener {
            popup.dismiss()
            // TODO: logout logic
        }
    }
}