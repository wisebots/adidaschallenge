package com.bolddevoteam.adidaschallenge.fitgoals.model.local

import androidx.room.*
import com.bolddevoteam.adidaschallenge.core.commons.DEFAULT_PROVIDER_ID
import com.bolddevoteam.adidaschallenge.core.commons.GOALS_TABLE_NAME
import com.bolddevoteam.adidaschallenge.fitgoals.model.remote.GoalsEntity

@Dao
interface GoalsDao {

    @Transaction
    suspend fun updateDataAndReturn(data: GoalsEntity): GoalsEntity {
        saveData(data)
        return getData(data.id ?: DEFAULT_PROVIDER_ID)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveData(data: GoalsEntity)

    @Query("SELECT * FROM $GOALS_TABLE_NAME WHERE id = :id LIMIT 1")
    suspend fun getData(id: String): GoalsEntity

    @Query("DELETE FROM $GOALS_TABLE_NAME WHERE id = :id")
    suspend fun deleteData(id: String)
}