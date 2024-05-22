package com.naji.pokemon.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomeDto(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)
