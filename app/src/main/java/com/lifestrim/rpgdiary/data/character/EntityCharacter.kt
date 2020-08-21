package com.lifestrim.rpgdiary.data.character

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "character_table")
data class EntityCharacter (
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "character_name")
    var characterName: String ,

    @ColumnInfo(name = "character_level")
    var characterLevel: Int,

    @ColumnInfo(name = "character_experience")
    var characterExperience: Int
)