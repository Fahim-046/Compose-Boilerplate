package com.fahimdev.data.repository

import com.fahimdev.core.network.ApiResult
import com.fahimdev.core.network.SafeApiRequest
import com.fahimdev.data.datasource.remote.CharacterApiService
import com.fahimdev.data.mapper.CharacterMapper
import com.fahimdev.domain.entities.Character
import com.fahimdev.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val characterApiService: CharacterApiService
) : CharacterRepository {

    override suspend fun getCharacters(page: Int): List<Character> {
        return when (val result = SafeApiRequest.apiRequest {
            characterApiService.getCharacters(page = page, limit = 10)
        }) {
            is ApiResult.Success -> result.data?.map(CharacterMapper::mapResponseToDomain) ?: emptyList()
            is ApiResult.Error, ApiResult.NetworkError -> emptyList()
        }
    }

    override suspend fun getCharacter(id: Int): Character? {
        return when (val result = SafeApiRequest.apiRequest {
            characterApiService.getCharacterById(id)
        }) {
            is ApiResult.Success -> result.data?.let(CharacterMapper::mapResponseToDomain)
            is ApiResult.Error, ApiResult.NetworkError -> null
        }
    }
}