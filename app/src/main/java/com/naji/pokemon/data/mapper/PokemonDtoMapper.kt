package com.naji.pokemon.data.mapper

import com.naji.pokemon.data.remote.response.PokemonDto
import com.naji.pokemon.domain.model.PokemonItem
import com.naji.pokemon.utils.Constants.Companion.BASE_URL

class PokemonDtoMapper : Mapper<PokemonDto, PokemonItem> {

    override fun mapFromEntity(entity: PokemonDto): PokemonItem {
        return PokemonItem(
            name = entity.name,
            id = entity.getId(),
            sprite = ""
        )
    }

    override fun mapToEntity(domain: PokemonItem): PokemonDto {
        throw UnsupportedOperationException("Not implemented")
    }

    private fun PokemonDto.getId(): Int = this.url
        .replace("${BASE_URL}pokemon/", "")
        .replace("/", "")
        .toInt()
}