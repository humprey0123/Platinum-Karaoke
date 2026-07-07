package com.example.platinumkaraoke

//import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import android.view.View


/**
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<View>(R.id.nav_home).setOnClickListener {
            showHome()
        }

        findViewById<View>(R.id.nav_search).setOnClickListener {
            showSearch()
        }

        if (savedInstanceState == null) {
            showHome()
        }
    }

    fun showHome() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, HomeFragment())
            .commit()

        findViewById<ImageView>(R.id.bg)
            .setImageResource(R.drawable.bg_home)
    }

    fun showSearch() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, SearchFragment())
            .commit()

        findViewById<ImageView>(R.id.bg)
            .setImageResource(R.drawable.bg_songlist)
    }

}