package com.palak.starwarsdemo.models

data class CharacterListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Character>
)