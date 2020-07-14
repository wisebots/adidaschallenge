package com.bolddevoteam.adidaschallenge.fitgoals.model.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bolddevoteam.adidaschallenge.core.commons.DEFAULT_PROVIDER_ID
import com.bolddevoteam.adidaschallenge.core.commons.GOALS_TABLE_NAME
import com.bolddevoteam.adidaschallenge.core.repository.DomainMapper
import com.bolddevoteam.adidaschallenge.fitgoals.utils.Converters

@Entity(tableName = GOALS_TABLE_NAME)
data class GoalsEntity(@PrimaryKey val id: String = DEFAULT_PROVIDER_ID,
                       @TypeConverters(Converters::class) val goals: Goals
) : DomainMapper<Goals> {

    override fun mapToDomainModel()= goals

}
