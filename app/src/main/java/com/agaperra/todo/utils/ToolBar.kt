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
import androidx.navigation.NavHostController
import com.agaperra.todo.R
import com.agaperra.todo.ui.theme.Color as color

@Composable
fun Toolbar(
    @StringRes list: List<Int>,
    navHostController: NavHostController
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
                DropdownMenuDemo(listOf(list[1]), navHostController)
            }
        }
    }
}

@Composable
fun DropdownMenuDemo(@StringRes list: List<Int>, navHostController: NavHostController) {

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
            DropdownMenuItem(onClick = {
                navHostController.navigate(route = "about")
            }) {
                Text (stringResource(id = list[0]),color = MaterialTheme.colors.primaryVariant)
            }

        }
    }

}