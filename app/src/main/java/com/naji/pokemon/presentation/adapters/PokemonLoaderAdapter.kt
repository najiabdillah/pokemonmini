package com.naji.pokemon.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naji.pokemon.databinding.ItemErrorLayoutBinding
import com.naji.pokemon.databinding.ItemProgressLayoutBinding

class PokemonLoaderAdapter : LoadStateAdapter<PokemonLoaderAdapter.ItemViewHolder>() {

    override fun getStateViewType(loadState: LoadState) = when(loadState) {
        is LoadState.NotLoading -> error("Not supported")
        is LoadState.Loading -> PROGRESS
        is LoadState.Error -> ERROR
    }

    override fun onBindViewHolder(holder: ItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemViewHolder {
        return when(loadState) {
            LoadState.Loading -> ProgressViewHolder(LayoutInflater.from(parent.context), parent)
            is LoadState.Error -> ErrorViewHolder(LayoutInflater.from(parent.context), parent)
            is LoadState.NotLoading -> error("Not supported")
        }
    }

    abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(loadState: LoadState)
    }

    class ProgressViewHolder internal constructor(
        binding: ItemProgressLayoutBinding
    ) : ItemViewHolder(binding.root) {
        override fun bind(loadState: LoadState) {  }
        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): ProgressViewHolder {
                return ProgressViewHolder(
                    ItemProgressLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }
    }

    class ErrorViewHolder internal constructor(
        private val binding: ItemErrorLayoutBinding
    ) : ItemViewHolder(binding.root) {
        override fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMessage.text = loadState.error.message ?: "error"
            }
        }
        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): ErrorViewHolder {
                return ErrorViewHolder(
                    ItemErrorLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }
    }

    private companion object {
        private const val ERROR = 1
        private const val PROGRESS = 0
    }
}