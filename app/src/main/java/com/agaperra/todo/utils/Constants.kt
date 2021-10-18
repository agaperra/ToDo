package com.agaperra.todo.utils

import android.annotation.SuppressLint
import com.agaperra.todo.data.db.entity.RoomNote
import java.text.SimpleDateFormat

object Constants {
    const val SPLASH_SCREEN_FIRST_LAUNCH_DELAY = 2500L
    const val SPLASH_SCREEN_NORMAL = 700L

    const val FIRST_LAUNCH_PREFERENCE_KEY = "isFirstLaunch"

    const val SPLASH_SCREEN = "splash"
    const val HOME_SCREEN = "home"
    const val ADD_SCREEN = "add"

    @SuppressLint("SimpleDateFormat")
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")

    var notes: List<RoomNote> = listOf<RoomNote>()

    const val ACTION_ITEM_SIZE = 56
    const val CARD_OFFSET = 112f
}