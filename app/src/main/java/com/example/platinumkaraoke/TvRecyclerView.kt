package com.example.platinumkaraoke

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

class TvRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var topView: View? = null

    override fun focusSearch(focused: View, direction: Int): View? {

        val lm = layoutManager as? LinearLayoutManager
            ?: return super.focusSearch(focused, direction)

        val currentPos = getChildAdapterPosition(focused)

        if (currentPos == NO_POSITION) {
            return super.focusSearch(focused, direction)
        }

        when (direction) {

            View.FOCUS_LEFT -> {
                val parent = topView as? ViewGroup
                val firstChild = parent?.getChildAt(0)

                firstChild?.requestFocus()
                return firstChild ?: focused
            }

            View.FOCUS_DOWN -> {
                val targetPos = currentPos + 1
                val targetView = lm.findViewByPosition(targetPos)
                return targetView ?: focused
            }
        }

        return super.focusSearch(focused, direction)
    }
}