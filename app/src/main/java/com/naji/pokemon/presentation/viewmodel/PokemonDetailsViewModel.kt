package com.naji.pokemon.presentation.viewmodel

import android.content.ContentResolver
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naji.pokemon.R
import com.naji.pokemon.data.DetailsNotFoundException
import com.naji.pokemon.domain.model.PokemonDetails
import com.naji.pokemon.domain.model.SpriteNames
import com.naji.pokemon.domain.model.Sprites
import com.naji.pokemon.domain.usecase.GetPokemonDetailsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsByIdUseCase: GetPokemonDetailsByIdUseCase,
    @ApplicationContext context: Context
) : ViewModel() {

    private val _pokemonDetails = MutableStateFlow(PokemonDetails())
    val pokemonDetails = _pokemonDetails.asStateFlow()

    private val spriteUri by lazy {
        val rId = R.drawable.baseline_error_24
        ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                context.resources.getResourcePackageName(rId) + '/' +
                context.resources.getResourceTypeName(rId) + '/' +
                context.resources.getResourceEntryName(rId)
    }

    fun loadDetails(id: Int, updateActionListener: UpdateActionListener) {
        viewModelScope.launch {
            updateActionListener.updateActionListener(true)
            val newDetails = try {
                getPokemonDetailsByIdUseCase.getDetails(id)
            } catch (e: DetailsNotFoundException) {
                val spriteName = SpriteNames.FrontShiny().name
                val sprites = mapOf(spriteName to spriteUri)

                PokemonDetails(name = e.message ?: "", sprites = Sprites(sprites))
            }

            _pokemonDetails.update { newDetails }
        }.invokeOnCompletion { updateActionListener.updateActionListener(false) }
    }
}

fun interface UpdateActionListener {
    fun updateActionListener(value: Boolean)
}
