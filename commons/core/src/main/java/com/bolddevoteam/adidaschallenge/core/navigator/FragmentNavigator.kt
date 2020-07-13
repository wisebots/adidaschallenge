package com.bolddevoteam.adidaschallenge.core.navigation

import androidx.navigation.NavDirections

interface FragmentNavigator {

    fun showFragmentDirections(id: Int, navDirections: NavDirections)
}