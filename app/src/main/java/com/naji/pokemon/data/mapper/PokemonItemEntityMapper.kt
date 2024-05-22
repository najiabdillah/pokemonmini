package com.naji.pokemon.data.mapper

import com.naji.pokemon.data.local.model.PokemonItemEntity
import com.naji.pokemon.domain.model.PokemonItem

class PokemonItemEntityMapper : Mapper<PokemonItemEntity, PokemonItem> {
    override fun mapFromEntity(entity: PokemonItemEntity): PokemonItem {
        return PokemonItem(
            name = entity.name,
            id = entity.id,
            sprite = entity.sprite,
            selected = entity.selected
        )
    }

    override fun mapToEntity(domain: PokemonItem): PokemonItemEntity {
        return PokemonItemEntity(
            name = domain.name,
            sprite = domain.sprite,
            id = domain.id,
            selected = domain.selected
        )
    }
}