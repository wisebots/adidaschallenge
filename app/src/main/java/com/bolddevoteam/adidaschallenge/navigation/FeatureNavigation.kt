package com.bolddevoteam.adidaschallenge.navigation

import android.content.Intent
import com.bolddevoteam.adidaschallenge.core.navigation.DynamicFeature
import com.bolddevoteam.adidaschallenge.core.navigation.loadIntentOrNull

val FITGOALS = "com.bolddevoteam.adidaschallenge.fitgoals.view.activities.GoalsActivity"

object FeatureNavigation : DynamicFeature<Intent> {

    override val dynamicStart: Intent?
        get() = FITGOALS.loadIntentOrNull()

    fun home(): Intent? = FITGOALS.loadIntentOrNull()
}