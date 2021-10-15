package com.agaperra.todo.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import com.agaperra.todo.ui.screens.home.HomeScreen
import com.agaperra.todo.utils.Constants.HOME_SCREEN
import com.google.accompanist.navigation.animation.composable

@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun NavGraphBuilder.homeComposable() {
    composable(route = HOME_SCREEN) { HomeScreen() }
}