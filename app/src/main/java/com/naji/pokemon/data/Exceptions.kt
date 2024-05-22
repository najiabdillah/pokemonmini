package com.naji.pokemon.data

import java.lang.RuntimeException

sealed class AppException : RuntimeException()

class DetailsNotFoundException : AppException()