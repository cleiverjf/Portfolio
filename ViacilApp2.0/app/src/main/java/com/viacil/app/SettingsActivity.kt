package com.viacil.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viacil.app.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Implement accessibility options, DataStore, download languages
    }
}