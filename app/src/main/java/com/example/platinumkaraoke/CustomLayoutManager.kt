package com.example.platinumkaraoke

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomLayoutManager(context: Context) : LinearLayoutManager(context) {

    override fun getExtraLayoutSpace(state: RecyclerView.State): Int {
        return 20
    }
}