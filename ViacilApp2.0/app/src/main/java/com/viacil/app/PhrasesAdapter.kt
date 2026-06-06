package com.viacil.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viacil.app.databinding.ItemPhraseBinding

class PhrasesAdapter(
    private val originalPhrases: List<String>,
    private val onSpeakClick: (String) -> Unit
) : RecyclerView.Adapter<PhrasesAdapter.PhraseViewHolder>() {

    private var filteredPhrases = originalPhrases.toMutableList()

    inner class PhraseViewHolder(private val binding: ItemPhraseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(phrase: String) {
            binding.tvPhrase.text = phrase
            binding.btnSpeak.setOnClickListener {
                onSpeakClick(phrase)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhraseViewHolder {
        val binding = ItemPhraseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhraseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhraseViewHolder, position: Int) {
        holder.bind(filteredPhrases[position])
    }

    override fun getItemCount() = filteredPhrases.size

    fun filter(query: String) {
        filteredPhrases = if (query.isEmpty()) {
            originalPhrases.toMutableList()
        } else {
            originalPhrases.filter {
                it.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }
}