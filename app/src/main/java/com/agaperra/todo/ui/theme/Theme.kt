package com.agaperra.todo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

import com.agaperra.todo.ui.theme.Color as colors

private val DarkColorPalette = darkColors(
    primary = colors.cinnamonPlum,
    primaryVariant = colors.cinnamonWhite,
    secondary = colors.cinnamonWine,
    background = colors.cinnamonBrown,
    surface = colors.cinnamonPlum
)

private val LightColorPalette = lightColors(
    primary = colors.pastelDeepDirtyRed,
    primaryVariant = colors.pastelDeepPurple,
    secondary = colors.pastelDirtyPink ,
    background = colors.pastelLightPurple,
    surface = colors.pastelDeepDirtyRed

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ToDoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}