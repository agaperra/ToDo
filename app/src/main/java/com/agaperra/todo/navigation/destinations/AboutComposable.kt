package com.agaperra.todo.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.agaperra.todo.ui.screens.about.AboutScreen
import com.agaperra.todo.ui.screens.add.AddScreen
import com.agaperra.todo.utils.Constants
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun NavGraphBuilder.aboutComposable(
    navHostController: NavHostController
) {
    composable(route = Constants.ABOUT_SCREEN) {
        AboutScreen(navHostController = navHostController)
    }
}