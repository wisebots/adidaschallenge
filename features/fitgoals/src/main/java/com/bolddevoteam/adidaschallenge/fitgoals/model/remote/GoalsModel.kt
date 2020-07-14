package com.bolddevoteam.adidaschallenge.fitgoals.model.remote

data class Goals (val items: List<GoalItem>?)

data class GoalItem (val id: String,
                     val title: String,
                     val description: String,
                     val type: String,
                     val goal: String,
                     val reward: Reward
)

data class Reward (val trophy: String,
                   val points: Int
)