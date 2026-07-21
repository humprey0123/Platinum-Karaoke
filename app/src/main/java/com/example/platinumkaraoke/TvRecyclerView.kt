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

    override fun focusSearch(focused: View, direction: Int): View? {

        val lm = layoutManager as? LinearLayoutManager ?: return super.focusSearch(focused, direction)
        val currentPos = getChildAdapterPosition(focused)

        if (currentPos == RecyclerView.NO_POSITION) {
            return super.focusSearch(focused, direction)
        }

        when (direction) {

            View.FOCUS_UP -> {
                val targetPos = currentPos - 1

            }

            View.FOCUS_DOWN -> {
                val targetPos = currentPos + 1

                val targetView = lm.findViewByPosition(targetPos)
                return targetView ?: focused
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