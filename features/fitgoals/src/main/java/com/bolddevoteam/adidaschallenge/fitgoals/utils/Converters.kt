package com.bolddevoteam.adidaschallenge.fitgoals.utils

import androidx.room.TypeConverter
import com.bolddevoteam.adidaschallenge.fitgoals.model.remote.Goals
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromGoalsToJson(goals: Goals?): String {
        return goals?.let { gson.toJson(it) } ?: ""
    }

    @TypeConverter
    fun fromJsonToGoals(json: String): Goals {
        val goals = object : TypeToken<Goals>() {}.type
        return gson.fromJson(json, goals)
    }

}