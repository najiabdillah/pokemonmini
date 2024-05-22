package com.naji.pokemon.domain.usecase

import com.naji.pokemon.data.repository.PokemonStateRepository

class UnselectPokemonUseCase(private val pokemonStateRepository: PokemonStateRepository) {

    suspend fun execute() = pokemonStateRepository.unselectPokemon()
}