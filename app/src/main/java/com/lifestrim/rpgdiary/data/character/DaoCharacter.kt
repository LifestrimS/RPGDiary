package com.lifestrim.rpgdiary.data.character

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoCharacter {
    @Insert
    suspend fun insertCharacter(character: EntityCharacter)

    @Update
    suspend fun updateCharacter(character: EntityCharacter)

    /*@Query("SELECT * FROM character_table WHERE id = :id")
    fun getCharacter(id: Long = 0): LiveData<EntityCharacter>*/
}