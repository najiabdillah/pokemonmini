package com.naji.pokemon.domain.di

import com.naji.pokemon.data.repository.PokemonDetailsRepository
import com.naji.pokemon.data.repository.PokemonListRepository
import com.naji.pokemon.data.repository.PokemonStateRepository
import com.naji.pokemon.domain.usecase.GetPokemonByNameUseCase
import com.naji.pokemon.domain.usecase.GetPokemonListUseCase
import com.naji.pokemon.domain.usecase.GetPokemonDetailsByIdUseCase
import com.naji.pokemon.domain.usecase.UnselectPokemonUseCase
import com.naji.pokemon.domain.usecase.UpdatePokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun providePokemonListUseCase(pokemonListRepository: PokemonListRepository): GetPokemonListUseCase {
        return GetPokemonListUseCase(pokemonListRepository)
    }

    @Provides
    fun providePokemonDetailsUseCase(detailsRepository: PokemonDetailsRepository): GetPokemonDetailsByIdUseCase {
        return GetPokemonDetailsByIdUseCase(detailsRepository)
    }

    @Provides
    fun providePokemonByNameUseCase(pokemonListRepository: PokemonListRepository): GetPokemonByNameUseCase {
        return GetPokemonByNameUseCase(pokemonListRepository)
    }

    @Provides
    fun provideUpdatePokemonSelectedUseCase(pokemonStateRepository: PokemonStateRepository): UpdatePokemonUseCase {
        return UpdatePokemonUseCase(pokemonStateRepository)
    }

    @Provides
    fun provideUnselectPokemonUseCase(pokemonStateRepository: PokemonStateRepository): UnselectPokemonUseCase {
        return UnselectPokemonUseCase(pokemonStateRepository)
    }
}