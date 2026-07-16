package com.example.platinumkaraoke

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

class CsvReader(private val context: Context) {

    fun loadAllSongs(): List<Song> {
        val songs = mutableListOf<Song>()

        try {
            val files = context.assets.list("") // 🔥 get all files in assets

            files?.forEach { fileName ->

                if (fileName.endsWith(".csv")) {

                    val inputStream = context.assets.open(fileName)
                    val reader = BufferedReader(InputStreamReader(inputStream))

                    var line: String?

                    while (reader.readLine().also { line = it } != null) {
                        val parts = line?.split(",")

                        if (parts != null && parts.size >= 3) {
                            val songNumber = parts[0].trim()
                            val title = parts[1].trim()
                            val artist = parts[2].trim()

                            songs.add(Song(songNumber, title, artist))
                        }
                    }

                    reader.close()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return songs
    }
}