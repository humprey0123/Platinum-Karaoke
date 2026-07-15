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
            showSearch() // ✅ now valid
        }

        if (savedInstanceState == null) {
            showHome()
        }
    }

    fun showHome() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, HomeFragment())
            .commit()

        findViewById<ImageView>(R.id.bg).apply {
            setImageResource(R.drawable.bg_home)
            setColorFilter(0x80000000.toInt())
        }
    }

    fun showSearch(selectedCategory: String? = null) {
        val fragment = SearchFragment()

        val bundle = Bundle()
        bundle.putString("selected_filter", selectedCategory)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, fragment) // ✅ FIXED
            .commit()

        findViewById<ImageView>(R.id.bg)
            .setImageResource(R.drawable.bg_songlist)
    }
}