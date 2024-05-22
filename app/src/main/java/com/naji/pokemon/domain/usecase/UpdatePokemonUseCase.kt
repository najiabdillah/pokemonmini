package com.naji.pokemon.domain.usecase

import com.naji.pokemon.data.repository.PokemonStateRepository

class UpdatePokemonUseCase(private val pokemonStateRepository: PokemonStateRepository) {

    suspend fun execute(id: Int, selected: Boolean) {
        pokemonStateRepository.selectPokemon(id, selected)
    }
}