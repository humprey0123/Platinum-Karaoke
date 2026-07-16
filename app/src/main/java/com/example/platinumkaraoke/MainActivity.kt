package com.example.platinumkaraoke

import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {

    private lateinit var bg: ImageView
    private lateinit var navHome: View
    private lateinit var navSearch: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views once (cleaner + faster)
        bg = findViewById(R.id.bg)
        navHome = findViewById(R.id.nav_home)
        navSearch = findViewById(R.id.nav_search)

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
}