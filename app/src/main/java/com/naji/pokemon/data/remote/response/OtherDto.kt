package com.naji.pokemon.data.remote.response

import com.google.gson.annotations.SerializedName

data class OtherDto(
    @SerializedName("dream_world") val dreamWorld: DreamWorldDto,
    @SerializedName("home") val home: HomeDto,
    @SerializedName("official-artwork") val officialArtworkDto: OfficialArtworkDto
)