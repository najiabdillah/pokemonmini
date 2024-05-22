package com.naji.pokemon.data.repository

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.naji.pokemon.data.di.DataModule
import com.naji.pokemon.data.local.model.PokemonItemEntity
import com.naji.pokemon.data.mapper.PokemonItemEntityMapper
import com.naji.pokemon.domain.model.PokemonItem
import com.naji.pokemon.domain.repository.IPokemonListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonListRepository @Inject constructor(
    private val cm: ConnectivityManager?,
    @DataModule.PagerOnline private val pagerOnline: Pager<Int, PokemonItemEntity>,
    @DataModule.PagerOffline private val pagerOffline: Pager<Int, PokemonItemEntity>
) : IPokemonListRepository {

    private val pokemonItemEntityMapper = PokemonItemEntityMapper()

    override fun getList(): Flow<PagingData<PokemonItem>> {
        return if (isConnected()) {
            getListOnline()
        } else {
            getListOffline()
        }
    }

    private fun getListOnline(): Flow<PagingData<PokemonItem>> {
        return pagerOnline.flow.map { pagingData ->
            pagingData.map { pokemonItemEntity ->
                pokemonItemEntityMapper.mapFromEntity(pokemonItemEntity)
            }
        }
    }

    private fun getListOffline(): Flow<PagingData<PokemonItem>> {
        return pagerOffline.flow.map { pagingData ->
            pagingData.map { pokemonItemEntity ->
                pokemonItemEntityMapper.mapFromEntity(pokemonItemEntity)
            }
        }
    }

    private fun isConnected(): Boolean {
        var isConnected = false
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                isConnected = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        return isConnected
    }
}