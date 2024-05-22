package com.naji.pokemon.domain.repository

import androidx.paging.PagingData
import com.naji.pokemon.domain.model.PokemonItem
import kotlinx.coroutines.flow.Flow

interface IPokemonListRepository {

    fun getList(): Flow<PagingData<PokemonItem>>
}