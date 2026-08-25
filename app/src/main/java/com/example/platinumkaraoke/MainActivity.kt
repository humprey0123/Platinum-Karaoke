package com.example.platinumkaraoke

import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : FragmentActivity() {

    private lateinit var bg: ImageView
    private lateinit var navHome: View
    private lateinit var navSearch: View
    private lateinit var searchOverlay: View
    private lateinit var navSettings: View
    private lateinit var navbar: View
    private var settingsPopup: SettingsPopup? = null
    private var expanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views once (cleaner + faster)
        bg = findViewById(R.id.bg)
        navHome = findViewById(R.id.nav_home)
        navSearch = findViewById(R.id.nav_search)
        navSettings = findViewById(R.id.nav_settings)
        searchOverlay = findViewById(R.id.search_overlay)
        navbar = findViewById(R.id.navbar)

        setupNavigation()

        onBackPressedDispatcher.addCallback(this) {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.main_content)

            if (searchOverlay.visibility == View.VISIBLE && expanded) {
                hideSearch()
            } else if (currentFragment !is HomeFragment) {
                showHome()
            } else if (navbar.visibility == View.GONE) {
                showHome()
                navbar.visibility = View.VISIBLE
            } else {
                finish()
            }
        }


        if (savedInstanceState == null) {
            showHome()
        }
    }

    private fun setupNavigation() {
        // Click (touch + remote enter)
        navHome.setOnClickListener { showHome() }
        navSearch.setOnClickListener { showSearch() }

        // Make touch behave like single tap (no double tap issue)
        val touchHandler = View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
                return@OnTouchListener true
            }
            false
        }

        navSettings.setOnClickListener {
            updateNavSelection(it)
            settingsPopup?.dismiss()

            settingsPopup = SettingsPopup(this)
            settingsPopup?.show(it)
        }

        // Touch Nav
        navHome.setOnTouchListener(touchHandler)
        navSearch.setOnTouchListener(touchHandler)
        navSettings.setOnTouchListener(touchHandler)
    }

    private fun updateNavSelection(selectedView: View?) {
        navHome.isSelected = selectedView == navHome
        navSearch.isSelected = selectedView == navSearch
    }

    fun showHome() {
        updateNavSelection(navHome)
        navbar.visibility = View.VISIBLE
        bg.setImageResource(R.drawable.bg_home)

        val transaction = supportFragmentManager.beginTransaction()
        hideSearch(transaction)
        transaction.replace(R.id.main_content, HomeFragment())
        transaction.commit()
    }

    fun showSettings(anchor: View) {
        updateNavSelection(navSettings)
        settingsPopup?.dismiss()

        navbar.visibility = View.GONE

        val transaction = supportFragmentManager.beginTransaction()
        hideSearch(transaction)
        transaction.replace(R.id.main_content, SettingsFragment())
        transaction.commit()

        bg.setImageResource(R.drawable.bg_songlist)
    }
//            setColorFilter(0x80000000.toInt())

    fun showSearch(selectedCategory: String? = null) {
        updateNavSelection(navSearch)
        val fragment = SearchFragment().apply {
            arguments = Bundle().apply {
                putString("selected_filter", selectedCategory)
            }
        }

        navbar.visibility = View.VISIBLE

        if (searchOverlay.visibility == View.VISIBLE) {
            hideSearch()
        } else {
        searchOverlay.visibility = View.VISIBLE
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.search_overlay, fragment)
            .replace(R.id.main_content, KaraokeFragment())
            .commit()

        bg.setImageResource(R.drawable.bg_home)
    }

    fun setSearchExpanded(value: Boolean) {
        expanded = value
    }

    fun hideSearch(transaction: FragmentTransaction? = null) {
        searchOverlay.visibility = View.GONE

        val fragment = supportFragmentManager.findFragmentById(R.id.search_overlay)
            ?: return

        if (transaction != null) {
            transaction.remove(fragment)
        } else {
            supportFragmentManager.beginTransaction()
                .remove(fragment)
                .commit()
        }
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
}