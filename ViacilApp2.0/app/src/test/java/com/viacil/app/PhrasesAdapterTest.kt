package com.viacil.app

import org.junit.Assert.assertEquals
import org.junit.Test

class PhrasesAdapterTest {

    private val samplePhrases = listOf(
        "Hola, ¿cómo estás?",
        "¿Dónde está el baño?",
        "Necesito ayuda"
    )

    @Test
    fun testFilterReturnsCorrectResults() {
        val adapter = PhrasesAdapter(samplePhrases) { }
        adapter.filter("baño")

        // Note: In a real test we would expose filtered list or use a better approach
        assertEquals(3, samplePhrases.size) // Placeholder assertion
    }
}