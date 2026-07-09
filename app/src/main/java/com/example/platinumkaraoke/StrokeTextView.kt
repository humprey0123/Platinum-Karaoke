package com.example.platinumkaraoke

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class StrokeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    private var strokeColor: Int = Color.BLACK
    private var strokeWidth: Float = 7f

    override fun onDraw(canvas: Canvas) {
        val textColor = currentTextColor

        // Draw stroke (outline)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        setTextColor(strokeColor)
        super.onDraw(canvas)

        // Draw fill (actual text)
        paint.style = Paint.Style.FILL
        setTextColor(textColor)
        super.onDraw(canvas)
    }
}