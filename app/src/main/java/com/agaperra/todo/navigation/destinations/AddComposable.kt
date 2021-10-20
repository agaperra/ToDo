package com.agaperra.todo.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.agaperra.todo.ui.screens.add.AddScreen
import com.agaperra.todo.utils.Constants
import com.agaperra.todo.utils.Constants.NOTE_DETAILS
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.DelicateCoroutinesApi
import timber.log.Timber

@DelicateCoroutinesApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addComposable(
    navHostController: NavHostController
) {
    composable(route = Constants.ROUTE_NOTE_DETAILS, arguments = listOf(navArgument("note") {
        type = NavType.IntType
    })) {
        AddScreen(navHostController = navHostController)
    }
}