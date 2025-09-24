package com.fahimdev.shared.domain.repository

import com.fahimdev.shared.domain.entities.Character

interface CharacterRepository {
    suspend fun getCharacters(page: Int): List<Character>
    suspend fun getCharacter(id: Int): Character?
}