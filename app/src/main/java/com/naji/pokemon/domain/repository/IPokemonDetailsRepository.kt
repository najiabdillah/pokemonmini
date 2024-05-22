package com.naji.pokemon.domain.repository

import com.naji.pokemon.domain.model.PokemonDetails

interface IPokemonDetailsRepository {

    suspend fun getDetails(id: Int): PokemonDetails
}