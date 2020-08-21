package com.lifestrim.rpgdiary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lifestrim.rpgdiary.data.character.DaoCharacter
import com.lifestrim.rpgdiary.data.character.EntityCharacter
import com.lifestrim.rpgdiary.data.task.DaoTask
import com.lifestrim.rpgdiary.data.task.EntityTask
import kotlinx.coroutines.CoroutineScope

@Database(entities = [EntityTask::class], version = 1, exportSchema = false)
abstract class RPGDatabase : RoomDatabase() {
    abstract fun taskDao(): DaoTask

    companion object {
        @Volatile
        private var INSTANCE: RPGDatabase? = null

        fun getInstance(context: Context): RPGDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RPGDatabase::class.java,
                        "rpg_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}