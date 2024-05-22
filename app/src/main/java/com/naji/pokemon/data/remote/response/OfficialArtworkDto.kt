package com.naji.pokemon.data.remote.response

import com.google.gson.annotations.SerializedName

data class OfficialArtworkDto(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?
)