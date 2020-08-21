package com.lifestrim.rpgdiary.data.character

import androidx.lifecycle.LiveData


class RepositoryCharacter (private val characterDao: DaoCharacter) {
    //val character: LiveData<EntityCharacter> = characterDao.getCharacter()

    suspend fun insertCharacter(character: EntityCharacter) {
        characterDao.insertCharacter(character)
    }

    suspend fun updateCharacter(character: EntityCharacter) {
        characterDao.updateCharacter(character)
    }

}