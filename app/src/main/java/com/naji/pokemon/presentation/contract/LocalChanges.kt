package com.naji.pokemon.presentation.contract

class LocalChanges {

    private val idsInProgress = mutableSetOf<Int>()
    private val selectedFlags = mutableMapOf<Int, Boolean>()

    fun selectItem(id: Int) {
        selectedFlags.merge(id, selectedFlags[id] ?: true) { _, _ ->
            selectedFlags[id]?.not() ?: true
        }
    }

    fun isSelected(id: Int) = selectedFlags[id]

    fun unselect() = selectedFlags.clear()
    fun isInProgress(id: Int): Boolean = idsInProgress.contains(id)

    fun isContainsSelected() = selectedFlags.containsValue(true)
}