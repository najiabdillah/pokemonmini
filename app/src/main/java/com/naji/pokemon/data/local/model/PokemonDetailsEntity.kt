package com.naji.pokemon.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.naji.pokemon.data.local.contract.RoomContract.DetailsTable
import com.naji.pokemon.data.remote.response.AbilityDto

@Entity(tableName = DetailsTable.TABLE_NAME)
data class PokemonDetailsEntity(
    @ColumnInfo(name = DetailsTable.COLUMN_NAME) val name: String,
    @ColumnInfo(name = DetailsTable.COLUMN_ABILITY_NAME) val abilityName: String,
    @ColumnInfo(name = DetailsTable.COLUMN_SPRITES) val sprites: List<String>,
    @ColumnInfo(name = DetailsTable.COLUMN_TYPES) val types: List<String>,
    @ColumnInfo(name = DetailsTable.COLUMN_WEIGHT_IN_HG) val weightInHg: Int,
    @ColumnInfo(name = DetailsTable.COLUMN_HEIGHT_IN_DM) val heightInDm: Int,
    @ColumnInfo(name = DetailsTable.COLUMN_lOCATION_AREA_ECOUNTERS) val locationAreaEncounters: String,
    @ColumnInfo(name = DetailsTable.COLUMN_ABILITIES) val ability: List<String>,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DetailsTable.COLUMN_ID)
    val id: Int
)
