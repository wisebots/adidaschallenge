package com.bolddevoteam.adidaschallenge.fitgoals.model.remote

import com.bolddevoteam.adidaschallenge.core.commons.DEFAULT_PROVIDER_ID
import com.bolddevoteam.adidaschallenge.core.repository.RoomMapper
import retrofit2.Response
import retrofit2.http.GET

interface GoalsApi {

    @GET("_ah/api/myApi/v1/goals")
    suspend fun getGoals() : Response<GoalsResponse>
}

data class GoalsResponse (val items : List<GoalItem>
) : RoomMapper<GoalsEntity> {

    override fun mapToRoomEntity() = GoalsEntity(DEFAULT_PROVIDER_ID, Goals(items))

}
