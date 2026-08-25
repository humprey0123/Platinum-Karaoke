package com.example.platinumkaraoke

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(
    private val onSongClick: (Song) -> Unit
) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    private var songs: List<Song> = listOf()

    fun submitList(newSongs: List<Song>) {
        songs = newSongs
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val songNumber: TextView = view.findViewById(R.id.songNumber)
        val title: TextView = view.findViewById(R.id.songTitle)
        val artist: TextView = view.findViewById(R.id.songArtist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = songs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]

        val touchHandler = View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
                return@OnTouchListener true
            }
            false
        }

        holder.itemView.setOnTouchListener(touchHandler)
        holder.itemView.setOnClickListener {
            onSongClick(song)
        }

        holder.songNumber.text = song.songNumber
        holder.title.text = song.title
        holder.artist.text = song.artist

        // ❗ Reset state (VERY IMPORTANT for RecyclerView reuse)
        holder.title.isSelected = false
        holder.artist.isSelected = false

        // 🔥 Trigger marquee when row is focused
        holder.itemView.setOnFocusChangeListener { _, hasFocus ->
            holder.title.isSelected = hasFocus
            holder.artist.isSelected = hasFocus
        }
    }

}
