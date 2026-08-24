package com.example.platinumkaraoke

import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class SearchKeyboardController(
    private val keyboard: LinearLayout,
    private val searchEditText: EditText,
    private val onQueryChanged: (String) -> Unit,
    private val onDone: () -> Unit
) {

    private var searchQuery: String = ""

    fun init() {
        disableSystemKeyboard()
        setupKeyboard()
        setupSearchClick()
    }

    private fun disableSystemKeyboard() {
        searchEditText.showSoftInputOnFocus = false
        searchEditText.isCursorVisible = false
    }

    private fun setupSearchClick() {
        searchEditText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN &&
                (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                showKeyboard()
                return@setOnKeyListener true
            }
            false
        }
    }

    fun handleGlobalFocusChange(newFocus: View?) {
        if (keyboard.visibility == View.VISIBLE) {
            val isInsideKeyboard = isViewChildOf(newFocus, keyboard)
            val isSearch = newFocus == searchEditText

            if (!isInsideKeyboard && !isSearch) {
                keyboard.visibility = View.GONE
            }
        }
    }

    private fun showKeyboard() {
        keyboard.visibility = View.VISIBLE

        keyboard.post {
            if (keyboard.childCount > 0) {
                keyboard.getChildAt(1).requestFocus()
            }
        }
    }

    private fun setupKeyboard() {
        val touchHandler = View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }
            false
        }

        for (i in 0 until keyboard.childCount) {
            val row = keyboard.getChildAt(i)

            if (row is LinearLayout) {
                for (j in 0 until row.childCount) {
                    val keyView = row.getChildAt(j)

                    if (keyView is TextView) {
                        keyView.setOnTouchListener(touchHandler)
                        keyView.setOnClickListener {
                            val key = keyView.text.toString()
                            handleKeyPress(key)
                        }
                    }
                }
            }
        }
    }

    private fun handleKeyPress(key: String) {
        when (key) {

            "SPACE" -> searchQuery += " "

            "⌫" -> {
                if (searchQuery.isNotEmpty()) {
                    searchQuery = searchQuery.dropLast(1)
                }
            }

            "DONE" -> {
                keyboard.visibility = View.GONE
                onDone()
                return
            }

            else -> searchQuery += key
        }

        searchEditText.setText(searchQuery)
        onQueryChanged(searchQuery)
    }

    private fun isViewChildOf(view: View?, parent: View): Boolean {
        var current = view
        while (current != null) {
            if (current == parent) return true
            current = current.parent as? View
        }
        return false
    }
}