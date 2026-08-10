package com.example.platinumkaraoke

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.leanback.app.ErrorSupportFragment

class ErrorFragment : ErrorSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.app_name)
    }

    fun setErrorContent() {
        imageDrawable = ContextCompat.getDrawable(
            requireContext(),
            androidx.leanback.R.drawable.lb_ic_sad_cloud
        )

        message = getString(R.string.error_fragment_message)
        setDefaultBackground(true)

        buttonText = getString(R.string.dismiss_error)
        buttonClickListener = View.OnClickListener {
            parentFragmentManager.beginTransaction()
                .remove(this@ErrorFragment)
                .commit()
        }
    }
}