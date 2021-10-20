package com.agaperra.todo.utils

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agaperra.todo.R
import com.agaperra.todo.ui.theme.Color as color

@Composable
fun Toolbar(
    @StringRes list: List<Int>
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(id = list[0]),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp)
                        .wrapContentSize()

                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
            ) {
                DropdownMenuDemo(listOf(list[1], list[2]))
            }
        }
    }
}

@Composable
fun DropdownMenuDemo(@StringRes list: List<Int>) {

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
                Text (stringResource(id = list[0]),color = MaterialTheme.colors.primaryVariant)
            }
            DropdownMenuItem(onClick = { }) {
                Text (stringResource(id = list[1]),color = MaterialTheme.colors.primaryVariant)
            }

        }
    }

}