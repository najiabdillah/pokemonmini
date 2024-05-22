package com.naji.pokemon.domain.usecase

import androidx.paging.PagingData
import androidx.paging.filter
import com.naji.pokemon.domain.model.PokemonItem
import com.naji.pokemon.domain.repository.IPokemonListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class GetPokemonByNameUseCase(private val pokemonListRepository: IPokemonListRepository) {

    fun execute(name: String): Flow<PagingData<PokemonItem>> {
        return pokemonListRepository.getList().transform { pagingData ->
            emit(pagingData.filter { it.name.contains(name, true) })
        }
    }
}