package com.ubaya.materialdesignw9

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ubaya.materialdesignw9.databinding.ActivityAddPlaylistBinding

class AddPlaylist : AppCompatActivity() {
    private lateinit var binding: ActivityAddPlaylistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlaylistBinding.inflate(layoutInflater)
        setContentView(binding .root)

    }
}