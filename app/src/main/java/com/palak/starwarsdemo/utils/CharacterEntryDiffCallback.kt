package com.palak.starwarsdemo.utils

import androidx.recyclerview.widget.DiffUtil
import com.palak.starwarsdemo.models.Character

/**
 * This is safety mechanism to only update the recyclerview if two items are really not equal.
 * Here we define equality of Character based on it's name.
 */
class CharacterEntryDiffCallback : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}