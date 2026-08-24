package com.example.platinumkaraoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SettingsScoreFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstance: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_settings_score,
            container,
            false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val score = view.findViewById<View>(R.id.fragment_settings_score)

        score.requestFocus()

        val touchHandler = View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
                return@OnTouchListener true
            }
            false
        }

        val scoreLevelOff = view.findViewById<View>(R.id.score_level_off)
        val scoreLevelCasual = view.findViewById<View>(R.id.score_level_casual)
        val scoreLevelExpert = view.findViewById<View>(R.id.score_level_expert)
        val scoreLevelPro = view.findViewById<View>(R.id.score_level_pro)

        val scoreAnimationDefault = view.findViewById<View>(R.id.score_animation_default)
        val scoreAnimationCustom = view.findViewById<View>(R.id.score_animation_custom)

        scoreLevelOff.setOnClickListener {
            setSelectedScoreLevel(scoreLevelOff)
        }
        scoreLevelOff.setOnTouchListener(touchHandler)

        scoreLevelCasual.setOnClickListener {
            setSelectedScoreLevel(scoreLevelCasual)
        }
        scoreLevelCasual.setOnTouchListener(touchHandler)

        scoreLevelExpert.setOnClickListener {
            setSelectedScoreLevel(scoreLevelExpert)
        }
        scoreLevelExpert.setOnTouchListener(touchHandler)

        scoreLevelPro.setOnClickListener {
            setSelectedScoreLevel(scoreLevelPro)
        }
        scoreLevelPro.setOnTouchListener(touchHandler)

        scoreAnimationDefault.setOnClickListener {
            setSelectedScoreAnimation(scoreAnimationDefault)
        }
        scoreAnimationDefault.setOnTouchListener(touchHandler)

        scoreAnimationCustom.setOnClickListener {
            setSelectedScoreAnimation(scoreAnimationCustom)
        }
        scoreAnimationCustom.setOnTouchListener(touchHandler)

        setSelectedScoreAnimation(scoreAnimationDefault)
        setSelectedScoreLevel(scoreLevelCasual)
    }

    private fun setSelectedScoreLevel (selectedView: View) {
        selectedView.isSelected = true

        val levelViews = listOf(
            view?.findViewById<View>(R.id.score_level_off),
            view?.findViewById<View>(R.id.score_level_casual),
            view?.findViewById<View>(R.id.score_level_expert),
            view?.findViewById<View>(R.id.score_level_pro),
        )

        levelViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }

    private fun setSelectedScoreAnimation (selectedView: View) {
        selectedView.isSelected = true

        val levelViews = listOf(
            view?.findViewById<View>(R.id.score_animation_custom),
            view?.findViewById<View>(R.id.score_animation_default)
        )

        levelViews.forEach { nav ->
            if (nav != selectedView) {
                nav?.isSelected = false
            }
        }
    }
}