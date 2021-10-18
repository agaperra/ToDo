package com.agaperra.todo.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import com.agaperra.todo.ui.screens.add.AddScreen
import com.agaperra.todo.utils.Constants
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.*

@DelicateCoroutinesApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addComposable(onClick: () -> Unit = {}) {
    composable(route = Constants.ADD_SCREEN) { AddScreen(onClick = onClick) }
}