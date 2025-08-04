package com.fahimdev.data.mapper

import com.fahimdev.data.model.CharacterResponse
import com.fahimdev.domain.entities.Character

class CharacterMapper {
    companion object {
        fun mapResponseToDomain(response: CharacterResponse): Character {
            return Character(
                id = response.id,
                name = response.name,
                ki = response.ki,
                maxKi = response.maxKi,
                race = response.race,
                gender = response.gender,
                description = response.description,
                affiliation = response.affiliation,
                image = response.image
            )
        }
    }
}