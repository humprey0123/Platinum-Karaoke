package com.example.platinumkaraoke

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import android.widget.ImageView

/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val bg = findViewById<ImageView>(R.id.dynamic_background)
        bg.setImageResource(R.drawable.bg_home)
    }
}