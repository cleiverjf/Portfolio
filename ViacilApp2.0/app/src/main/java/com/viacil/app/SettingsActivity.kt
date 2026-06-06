package com.viacil.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.viacil.app.databinding.ActivitySettingsBinding
import com.viacil.app.utils.DataStoreManager
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStoreManager = DataStoreManager(this)

        // Load preferences
        lifecycleScope.launch {
            dataStoreManager.highContrast.collect { enabled ->
                binding.switchHighContrast.isChecked = enabled
            }
        }

        // Save on change
        binding.switchHighContrast.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                dataStoreManager.setHighContrast(isChecked)
            }
        }

        // Similar for other switches...
    }
}