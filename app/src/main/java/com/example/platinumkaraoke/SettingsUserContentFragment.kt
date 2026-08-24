package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SettingsUserContentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_settings_user_content,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = view.findViewById<View>(R.id.fragment_settings_user_content)
        content.requestFocus()

        // Song Registration
        setupGroupSelection(
            listOf(R.id.song_registration_user_mp3, R.id.song_registration_user_mtv),
            R.id.song_registration_user_mp3
        )

        // BGV Registration
        setupGroupSelection(
            listOf(R.id.bgv_regis_video, R.id.bgv_regis_photo),
            R.id.bgv_regis_photo
        )

        // Score Meme Registration
        setupGroupSelection(
            listOf(R.id.score_meme_reg_clip),
            R.id.score_meme_reg_clip
        )

        // Management (MP3/MTV)
        setupGroupSelection(
            listOf(R.id.management_user_mp3, R.id.management_user_mtv),
            R.id.management_user_mp3
        )

        // Management (BGV)
        setupGroupSelection(
            listOf(R.id.management_bgv_video, R.id.management_bgv_photo),
            R.id.management_bgv_photo
        )

        // Management (Score/List)
        setupGroupSelection(
            listOf(R.id.management_score_clip, R.id.management_get_song_list),
            R.id.management_score_clip
        )
    }

    private fun setupGroupSelection(viewIds: List<Int>, defaultId: Int) {
        val views = viewIds.mapNotNull { view?.findViewById<View>(it) }

        val touchHandler = View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
                return@OnTouchListener true
            }
            false
        }

        views.forEach { v ->
            v.setOnClickListener {
                updateSelection(views, it)
            }
            v.setOnTouchListener(touchHandler)
        }

        // Set initial selection
        view?.findViewById<View>(defaultId)?.let {
            updateSelection(views, it)
        }
    }

    private fun updateSelection(group: List<View>, selectedView: View) {
        group.forEach { it.isSelected = (it == selectedView) }
    }
}