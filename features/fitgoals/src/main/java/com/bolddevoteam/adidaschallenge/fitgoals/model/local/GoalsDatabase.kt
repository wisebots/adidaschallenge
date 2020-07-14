package com.bolddevoteam.adidaschallenge.fitgoals.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bolddevoteam.adidaschallenge.fitgoals.model.remote.GoalsEntity
import com.bolddevoteam.adidaschallenge.fitgoals.utils.Converters

@Database(entities = [GoalsEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GoalsDatabase : RoomDatabase() {

    abstract fun searchGoalsDao(): GoalsDao

}