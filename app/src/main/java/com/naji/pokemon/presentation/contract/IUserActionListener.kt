package com.naji.pokemon.presentation.contract

import com.naji.pokemon.domain.model.PokemonItem

interface IUserActionListener {
    fun onOpen(pokemonItem: PokemonItem)
    fun onSelect(pokemonItem: PokemonItem)
}