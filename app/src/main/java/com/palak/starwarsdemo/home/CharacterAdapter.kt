package com.palak.starwarsdemo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.palak.starwarsdemo.R
import com.palak.starwarsdemo.databinding.ItemCharacterBinding
import com.palak.starwarsdemo.models.Character
import com.palak.starwarsdemo.utils.CharacterEntryDiffCallback

/**
 * This adapter loads character data in home screen.
 */
class CharacterAdapter(var onClick: (Character, Int) -> Unit) :
    PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterEntryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_character, parent, false
            ), onClick
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position) as Character)
    }

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding,
        var onClick: (Character, Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            with(binding) {
                binding.container.setOnClickListener {
                    onClick(character, bindingAdapterPosition)
                }
                binding.character = character
                executePendingBindings()
            }
        }
    }
}

