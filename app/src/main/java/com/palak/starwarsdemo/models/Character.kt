package com.palak.starwarsdemo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    var birth_year: String? = null,
    var created: String? = null,
    var edited: String? = null,
    var eye_color: String? = null,
    var films: List<String>? = null,
    var gender: String? = null,
    var hair_color: String? = null,
    var height: String? = null,
    var homeworld: String? = null,
    var mass: String? = null,
    var name: String? = null,
    var skin_color: String? = null,
    var species: List<String>? = null,
    var starships: List<String>? = null,
    var url: String? = null,
    var vehicles: List<String>? = null
) : Parcelable