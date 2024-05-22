package com.naji.pokemon.data.mapper

import com.naji.pokemon.data.remote.response.AbilityDto
import com.naji.pokemon.data.remote.response.TypeDto

class TypeDtoMapper : Mapper<List<TypeDto>, List<String>> {
    override fun mapFromEntity(entity: List<TypeDto>): List<String> {
        return entity.map { it.typeXDto.name }
    }

    override fun mapToEntity(domain: List<String>): List<TypeDto> {
        throw UnsupportedOperationException("Not implemented")
    }
}
class AbilityDtoMapper : Mapper<List<AbilityDto>, List<String>> {
    override fun mapFromEntity(entity: List<AbilityDto>): List<String> {
        return entity.map { it.name.toString() }
    }

    override fun mapToEntity(domain: List<String>): List<AbilityDto> {
        throw UnsupportedOperationException("Not implemented")
    }
}