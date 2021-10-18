package com.agaperra.todo.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import com.agaperra.todo.data.db.entity.RoomNote
import com.agaperra.todo.ui.screens.home.HomeScreen
import com.agaperra.todo.utils.Constants.HOME_SCREEN
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun NavGraphBuilder.homeComposable(
    navigateToAddScreen: () -> Unit
) {
    composable(route = HOME_SCREEN) { HomeScreen(navigateToAddScreen = navigateToAddScreen) }
}