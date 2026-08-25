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

    var strokeColor: Int = Color.BLACK
        set(value) {
            field = value
            invalidate()
        }
    var strokeWidth: Float = 6f
        set(value) {
            field = value
            invalidate()
        }
    private var isDrawing: Boolean = false

    override fun onDraw(canvas: Canvas) {
        if (isDrawing) return // Prevent recursion from setTextColor

        if (strokeWidth > 0) {
            isDrawing = true
            val textColor = currentTextColor

            // Draw stroke (outline)
            val p = paint
            p.style = Paint.Style.STROKE
            p.strokeWidth = strokeWidth
            p.strokeJoin = Paint.Join.ROUND
            
            // Temporarily disable shadow for the stroke pass
            val sColor = shadowColor
            val sDx = shadowDx
            val sDy = shadowDy
            val sRadius = shadowRadius
            setShadowLayer(0f, 0f, 0f, 0)
            
            setTextColor(strokeColor)
            super.onDraw(canvas)

            // Draw fill (actual text)
            p.style = Paint.Style.FILL
            setTextColor(textColor)
            setShadowLayer(sRadius, sDx, sDy, sColor)
            isDrawing = false
        }
        super.onDraw(canvas)
    }
}