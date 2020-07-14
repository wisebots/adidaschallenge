package com.bolddevoteam.adidaschallenge.core.navigation

import androidx.navigation.NavGraph

interface NavigationGraphRoute {

    var navGraph: NavGraph

    val graphName: String

    val packageName: String
}