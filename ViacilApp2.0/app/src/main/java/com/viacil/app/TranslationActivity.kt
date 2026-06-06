package com.viacil.app

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import com.viacil.app.databinding.ActivityTranslationBinding
import java.util.*

class TranslationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTranslationBinding
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTTS()

        // Example: receive text from camera
        val receivedText = intent.getStringExtra("detected_text") ?: ""
        binding.tvOriginal.text = receivedText

        binding.btnSpeakOriginal.setOnClickListener {
            tts.speak(receivedText, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    private fun setupTTS() {
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.getDefault()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.shutdown()
    }
}