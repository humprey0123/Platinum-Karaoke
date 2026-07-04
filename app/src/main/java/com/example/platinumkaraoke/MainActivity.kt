package com.example.platinumkaraoke

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import android.view.View

/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val bg = findViewById<View>(R.id.dynamic_background)
        bg.setBackgroundResource(R.drawable.bg_home)
    }
}