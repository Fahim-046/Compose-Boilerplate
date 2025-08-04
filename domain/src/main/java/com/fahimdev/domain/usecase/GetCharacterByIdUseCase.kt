package com.fahimdev.domain.usecase

import com.fahimdev.domain.entities.Character
import com.fahimdev.domain.repository.CharacterRepository

class GetCharacterByIdUseCase(private val characterRepository: CharacterRepository) {
    suspend fun invoke(id: Int) = characterRepository.getCharacter(id)
}