package com.agaperra.todo.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import com.agaperra.todo.navigation.destinations.aboutComposable
import com.agaperra.todo.navigation.destinations.addComposable
import com.agaperra.todo.navigation.destinations.homeComposable
import com.agaperra.todo.navigation.destinations.splashComposable
import com.agaperra.todo.utils.Constants.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.AnimatedNavHost
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SetupNavigation(navHostController: NavHostController) {
    val screens = remember(navHostController) { Screens(navHostController = navHostController) }

    AnimatedNavHost(navController = navHostController, startDestination = SPLASH_SCREEN, builder = {
        splashComposable(navigateToHomeScreen = screens.splash)
        homeComposable(navHostController)
        addComposable(navHostController)
        aboutComposable(navHostController)
    })
}