package com.example.platinumkaraoke

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomLayoutManager(context: Context) : LinearLayoutManager(context) {

    @Deprecated("getExtraLayoutSpace is deprecated in RecyclerView")
    override fun getExtraLayoutSpace(state: RecyclerView.State): Int {
        return height
    }
}