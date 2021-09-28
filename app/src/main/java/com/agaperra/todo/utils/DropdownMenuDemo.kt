package com.agaperra.todo.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.agaperra.todo.R

@Composable
fun DropdownMenuDemo() {

    val expanded = remember { mutableStateOf(false) }

    IconButton(onClick = { expanded.value = true }) {
        Icon(
            Icons.Filled.MoreVert,
            contentDescription = "Localized description"
        )
    }
    Box(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopEnd),
    ) {
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.wrapContentSize()
        ) {
            DropdownMenuItem(onClick = { }) {
                Text (stringResource(id = R.string.settings),color = MaterialTheme.colors.primaryVariant)
            }
            DropdownMenuItem(onClick = { }) {
                Text (stringResource(id = R.string.about),color = MaterialTheme.colors.primaryVariant)
            }

        }
    }

}