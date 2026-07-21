package com.example.platinumkaraoke

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TvRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    override fun focusSearch(focused: View?, direction: Int): View? {

        if (direction == View.FOCUS_UP) {
            val lm = layoutManager as? LinearLayoutManager
            val first = lm?.findFirstCompletelyVisibleItemPosition()
                ?: NO_POSITION

            // 🚫 If at top, DO NOT allow focus to escape
            if (first == 0) {
                return focused
            }
        }

        return super.focusSearch(focused, direction)
    }

    override fun onFocusChanged(
        gainFocus: Boolean,
        direction: Int,
        previouslyFocusedRect: Rect?
    ) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)

        if (gainFocus) {
            post {
                val lm = layoutManager as? LinearLayoutManager
                val first = lm?.findFirstVisibleItemPosition() ?: 0

                val view = lm?.findViewByPosition(first)
                view?.requestFocus()
            }
        }
    }
}