package com.fahimdev.domain.usecase

import com.fahimdev.domain.repository.CharacterRepository

class GetCharacterListUseCase(private val characterRepository: CharacterRepository){
    suspend fun invoke(page: Int) = characterRepository.getCharacters(page)
}