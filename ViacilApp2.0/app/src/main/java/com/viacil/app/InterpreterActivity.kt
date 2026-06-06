package com.viacil.app

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.viacil.app.databinding.ActivityInterpreterBinding
import java.util.*

class InterpreterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInterpreterBinding
    private lateinit var adapter: PhrasesAdapter
    private lateinit var tts: TextToSpeech
    private val phrases = listOf(
        "Hola, ¿cómo estás?",
        "¿Dónde está el baño?",
        "Necesito ayuda",
        "Gracias",
        "Por favor",
        "¿Puedes repetir?",
        "No entiendo",
        "¿Hablas inglés?",
        "Me llamo...",
        "¿Cuánto cuesta?"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterpreterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTTS()
        setupRecyclerView()
        setupSearch()
    }

    private fun setupTTS() {
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.getDefault()
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = PhrasesAdapter(phrases) { phrase ->
            tts.speak(phrase, TextToSpeech.QUEUE_FLUSH, null, null)
        }
        binding.recyclerPhrases.layoutManager = LinearLayoutManager(this)
        binding.recyclerPhrases.adapter = adapter
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText.orEmpty())
                return true
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.shutdown()
    }
}