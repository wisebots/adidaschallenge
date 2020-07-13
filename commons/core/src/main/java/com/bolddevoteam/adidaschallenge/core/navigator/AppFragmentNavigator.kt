package com.bolddevoteam.adidaschallenge.core.navigation

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

class AppFragmentNavigator(private val activity: FragmentActivity) :
    FragmentNavigator {

    override fun showFragmentDirections(id: Int, navDirections: NavDirections) = activity.findNavController(id).navigate(navDirections)

}
