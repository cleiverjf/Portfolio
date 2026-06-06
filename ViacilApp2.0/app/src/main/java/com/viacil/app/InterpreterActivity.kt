package com.viacil.app

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.viacil.app.databinding.ActivityInterpreterBinding
import java.util.*

class InterpreterActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityInterpreterBinding
    private lateinit var tts: TextToSpeech
    private lateinit var adapter: PhrasesAdapter
    private val allPhrases = mutableListOf<Phrase>()

    data class Phrase(val text: String, val category: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterpreterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tts = TextToSpeech(this, this)

        setupPhrases()
        setupRecyclerView()
        setupSearch()
    }

    private fun setupPhrases() {
        allPhrases.addAll(listOf(
            Phrase("Hola, ¿cómo estás?", "Saludos"),
            Phrase("Necesito ayuda", "Ayuda"),
            Phrase("¿Dónde está el baño?", "Dirección"),
            Phrase("Gracias", "Cortesía"),
            Phrase("No entiendo", "Comunicación"),
            Phrase("Por favor", "Cortesía"),
            Phrase("¿Cuánto cuesta?", "Compras")
        ))
        adapter = PhrasesAdapter(allPhrases) { phrase ->
            speak(phrase.text)
        }
    }

    private fun setupRecyclerView() {
        binding.rvPhrases.adapter = adapter
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                val query = s.toString().trim()
                val filtered = if (query.isEmpty()) allPhrases else allPhrases.filter {
                    it.text.contains(query, ignoreCase = true)
                }
                adapter.updateList(filtered)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        Toast.makeText(this, "Hablando: $text", Toast.LENGTH_SHORT).show()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale("es", "ES")
        }
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}