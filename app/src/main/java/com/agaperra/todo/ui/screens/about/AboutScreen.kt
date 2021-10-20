package com.agaperra.todo.ui.screens.about

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.agaperra.todo.R
import kotlinx.coroutines.DelicateCoroutinesApi

@SuppressLint("CoroutineCreationDuringComposition")
@DelicateCoroutinesApi
@ExperimentalComposeUiApi
@Composable
fun AboutScreen(
    navHostController: NavHostController
) {

    MaterialTheme(
        typography =
        Typography(
            body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = MaterialTheme.colors.primaryVariant
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp, 0.dp, 30.dp, 0.dp)
        ) {

            Row(
                modifier = Modifier
                    .padding(0.dp, 8.dp, 0.dp, 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    modifier =
                    Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    FloatingActionButton(
                        onClick = {
                            navHostController.navigate(route = "home") {
                                popUpTo(0)
                            }
                        },
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.primaryVariant,
                    ) {
                        Icon(Icons.Filled.ArrowBack, "")
                    }
                }

            }
            Row(
                modifier = Modifier
                    .padding(30.dp, 0.dp, 30.dp, 0.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.info),
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.primaryVariant,

                    )
            }
        }
    }
}



