package com.fahimdev.domain.repository

import com.fahimdev.domain.entities.Character

interface CharacterRepository {
    suspend fun getCharacters(page: Int): List<Character>

    suspend fun getCharacter(id: Int): Character?
}