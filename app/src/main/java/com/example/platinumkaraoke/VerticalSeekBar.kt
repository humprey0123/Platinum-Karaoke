package com.example.platinumkaraoke

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.SeekBar

class VerticalSeekBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SeekBar(context, attrs, defStyleAttr) {

    init {
        thumbOffset = 0
    }

    private val tickPaint = Paint().apply {
        color = 0xFFFFFFFF.toInt()
        strokeWidth = 2f
        style = Paint.Style.STROKE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec)
        setMeasuredDimension(measuredHeight, measuredWidth)
    }

    override fun onDraw(canvas: Canvas) {
        // Draw tick marks first
        drawTickMarks(canvas)

        canvas.rotate(-90f)
        canvas.translate(-height.toFloat(), 0f)

        // Ensure thumb and progress drawable bounds are correct for the rotated state
        progressDrawable?.setBounds(0, 0, height, width)
        thumb?.run {
            val thumbWidth = intrinsicWidth
            val thumbHeight = intrinsicHeight
            val availableWidth = height
            val ratio = if (max > 0) progress.toFloat() / max else 0f
            val thumbX = (ratio * availableWidth).toInt()
            val thumbY = width / 2
            setBounds(
                thumbX - thumbWidth / 2,
                thumbY - thumbHeight / 2,
                thumbX + thumbWidth / 2,
                thumbY + thumbHeight / 2
            )
        }

        super.onDraw(canvas)
    }

    private fun drawTickMarks(canvas: Canvas) {
        val count = 20
        // Padding for the thumb radius so ticks align with the track range
        val thumbRadius = 10f
        val startY = thumbRadius + paddingTop
        val endY = height.toFloat() - thumbRadius - paddingBottom
        val step = (endY - startY) / count

        val centerX = width / 2f
        val tickStartX = centerX + 10f // Offset from track center to the right
        val leftLineX = centerX // center the line

        // Draw the vertical line on the left
        canvas.drawLine(leftLineX, startY, leftLineX, endY, tickPaint)

        for (i in 0..count) {
            val y = startY + i * step
            // Longer ticks every 5 steps
            val tickWidth = if (i % 5 == 0) 15f else 8f
            canvas.drawLine(tickStartX, y, tickStartX + tickWidth, y, tickPaint)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(h, w, oldh, oldw)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (!isEnabled) {
            return super.onKeyDown(keyCode, event)
        }

        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> {
                progress += keyProgressIncrement
                return true
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                progress -= keyProgressIncrement
                return true
            }
            KeyEvent.KEYCODE_DPAD_LEFT,
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                // Return false to allow focus movement instead of seekbar progress change
                return false
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) {
            return false
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_MOVE,
            MotionEvent.ACTION_UP -> {
                val thumbRadius = 10f
                val startY = thumbRadius + paddingTop
                val endY = height.toFloat() - thumbRadius - paddingBottom
                val trackHeight = endY - startY
                
                val relativeY = event.y - startY
                val ratio = relativeY / trackHeight
                progress = (max - (ratio * max)).toInt().coerceIn(0, max)
                
                onSizeChanged(width, height, 0, 0) // Force thumb bound update
                performClick()
            }
        }
        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }
}