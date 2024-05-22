package com.naji.pokemon.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.naji.pokemon.R
import com.naji.pokemon.databinding.FragmentPokemonDetailsBinding
import com.naji.pokemon.domain.model.PokemonDetails
import com.naji.pokemon.presentation.adapters.ImagesListAdapter
import com.naji.pokemon.presentation.viewmodel.PokemonDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment() {

    private var _binding: FragmentPokemonDetailsBinding? = null

    private val binding: FragmentPokemonDetailsBinding get() = _binding!!

    private val viewModel: PokemonDetailsViewModel by viewModels()

    private val adapter = ImagesListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonDetailsBinding.bind(view)

        setUpFragment(requireArguments().getInt(ARG_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setUpFragment(id: Int) {
        binding.progressCircular.visibility = View.VISIBLE
        viewModel.loadDetails(id) { updateActionListener(it) }

        lifecycleScope.launch {
            viewModel.pokemonDetails.collectLatest {
                (requireActivity() as PokemonActivity).apply {
                    supportActionBar?.title = it.locationAreaEncounters.replace("https://pokeapi.co/api/v2/pokemon/${it.id}/","")
                }
                binding.pokemonName.text = it.name
                Log.d("KONTOLPANJANG", "${it.abilitiesXDto}")
                Log.d("KONTOLPANJANG", "${it.abilityName}")
                Log.d("KONTOL", "${it.abilitiesXDto}")
                Log.d("KONTOL", "${it.types}")
                binding.pokemonHeight.text = getString(R.string.height, it.heightInDm.toString())
                binding.abilityPokemon.text = it.abilityName
                binding.pokemonWeight.text = getString(R.string.weight, it.weightInHg.toString())
                it.types.forEach { type -> addType(type) }
//                it.abilitiesXDto.forEach { type -> addTypeAbility(type) }
                setUpRecyclerView(it)
            }
        }
    }

    private fun setUpRecyclerView(pokemonDetails: PokemonDetails) {
        val images = mutableListOf<String>()
        pokemonDetails.sprites.sprites.forEach { entry ->
            if (entry.value != null) {
                images.add(entry.value!!)
            }
        }
        adapter.setImages(images)
        binding.recyclerView.onFlingListener = null
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
    }

    private fun getString(@StringRes resId: Int, data: String): String {
        return requireContext().getString(resId, data)
    }

    private fun addType(type: String) {
        val layout = LayoutInflater.from(requireContext())
            .inflate(R.layout.types_layout_item, binding.typesLayout, false)

        val textView = layout.findViewById<TextView>(R.id.type_name)
        textView.text = type.replaceFirstChar { it.uppercaseChar() }

        binding.typesLayout.addView(layout)
    }
    private fun addTypeAbility(type: String) {
        val layout = LayoutInflater.from(requireContext())
            .inflate(R.layout.ability_layout_item, binding.abilityLayout, false)

        val textView = layout.findViewById<TextView>(R.id.ability_name)
        textView.text = type.replaceFirstChar { it.uppercaseChar() }

        binding.typesLayout.addView(layout)
    }

    companion object {
        const val ARG_ID = "id"
    }

    private fun updateActionListener(value: Boolean) {
        with(binding.progressCircular) {
            visibility = if (value) {
                binding.setInvisible()
                View.VISIBLE
            } else {
                binding.setVisible()
                View.GONE
            }
        }
    }

    private fun FragmentPokemonDetailsBinding.setInvisible() {
        pokemonHeight.visibility = View.INVISIBLE
        pokemonWeight.visibility = View.INVISIBLE
        pokemonName.visibility = View.INVISIBLE
        recyclerView.visibility = View.INVISIBLE
    }

    private fun FragmentPokemonDetailsBinding.setVisible() {
        pokemonHeight.visibility = View.VISIBLE
        pokemonWeight.visibility = View.VISIBLE
        pokemonName.visibility = View.VISIBLE
        recyclerView.visibility = View.VISIBLE
    }
}