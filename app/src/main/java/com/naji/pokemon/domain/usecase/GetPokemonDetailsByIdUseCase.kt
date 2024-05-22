package com.naji.pokemon.domain.usecase

import com.naji.pokemon.data.DetailsNotFoundException
import com.naji.pokemon.domain.model.PokemonDetails
import com.naji.pokemon.domain.repository.IPokemonDetailsRepository
import kotlin.jvm.Throws

class GetPokemonDetailsByIdUseCase(private val pokemonDetailsRepository: IPokemonDetailsRepository) {

    /**
    * @throws DetailsNotFoundException
    * @param id identifier of pokemon
    * */
    @Throws(DetailsNotFoundException::class)
    suspend fun getDetails(id: Int): PokemonDetails {
        return pokemonDetailsRepository.getDetails(id)
    }
}