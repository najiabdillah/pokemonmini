package com.naji.pokemon.domain.usecase

import androidx.paging.PagingData
import com.naji.pokemon.domain.model.PokemonItem
import com.naji.pokemon.domain.repository.IPokemonListRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(private val pokemonListRepository: IPokemonListRepository) {

    fun execute(): Flow<PagingData<PokemonItem>> {
        return pokemonListRepository.getList()
    }
}