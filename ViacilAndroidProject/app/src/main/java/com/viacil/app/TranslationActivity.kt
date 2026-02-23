package com.viacil.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TranslationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)
        // Aquí se mostrará el texto original, la traducción y el botón de TTS
    }
}
