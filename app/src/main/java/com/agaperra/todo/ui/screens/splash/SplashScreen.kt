package com.agaperra.todo.ui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.agaperra.todo.R
import com.agaperra.todo.ui.viewModel.SharedViewModel
import com.agaperra.todo.utils.Constants.SPLASH_SCREEN_FIRST_LAUNCH_DELAY
import com.agaperra.todo.utils.Constants.SPLASH_SCREEN_NORMAL
import com.agaperra.todo.utils.Constants.notes
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@SuppressLint("CoroutineCreationDuringComposition")
@DelicateCoroutinesApi
@Composable
fun SplashScreen(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
) {
    val isFirstLaunch by sharedViewModel.isFirstLaunch.collectAsState()


        sharedViewModel.readAllNote.observeForever { notes = it ?: listOf() }


    LaunchedEffect(key1 = isFirstLaunch) {
        delay(
            timeMillis = if (isFirstLaunch) SPLASH_SCREEN_FIRST_LAUNCH_DELAY
            else SPLASH_SCREEN_NORMAL
        )
        navigateToHomeScreen()
        Timber.d("Navigate to home screen")
    }
    SplashBackground()
}

@Composable
fun SplashBackground(backgroundColor: Color = MaterialTheme.colors.surface) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = backgroundColor
    ) {

        val systemUiController = rememberSystemUiController()
        SideEffect { systemUiController.setSystemBarsColor(color = backgroundColor) }

        Image(
            painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(R.string.splash_screen_background),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.wrapContentSize()
        )
    }
}