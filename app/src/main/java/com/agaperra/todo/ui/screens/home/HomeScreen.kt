package com.agaperra.todo.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.agaperra.todo.R
import com.agaperra.todo.ui.card.CardContent
import com.agaperra.todo.ui.theme.ToDoTheme
import com.agaperra.todo.ui.viewModel.SharedViewModel
import com.agaperra.todo.utils.Constants.notes
import com.agaperra.todo.utils.Toolbar
import kotlinx.coroutines.*


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@DelicateCoroutinesApi
@Composable
fun HomeScreen(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    ToDoTheme {
        Column() {
            Surface() {
                Toolbar(listOf(R.string.app_name, R.string.settings, R.string.about))
            }
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                color = MaterialTheme.colors.surface
            ) {
                Content(sharedViewModel, navHostController)
                Row(
                    modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 8.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    val onClick = {
                        navHostController.navigate(route = "add/" + -1)
                    }
                    FloatingActionButton(
                        onClick = onClick,
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.primaryVariant,
                    ) {
                        Icon(Icons.Filled.Add, "")
                    }
                }

            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@SuppressLint("CoroutineCreationDuringComposition")
@DelicateCoroutinesApi
@Composable
fun Content(sharedViewModel: SharedViewModel, navHostController: NavHostController) {

    sharedViewModel.readAllNote.observeForever { notes = it }
    var state by remember { mutableStateOf(notes) }


    if (notes.isEmpty()) {
        Image(
            painterResource(R.drawable.ic_box_empty),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.background),
            modifier = Modifier.wrapContentSize()
        )
    } else {
        var columnAppeared by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            columnAppeared = true
        }
        LazyColumn {
            itemsIndexed(
                items = notes
            ) { _, card ->

                val dismissState = rememberDismissState()
                val dismissDirection = dismissState.dismissDirection
                val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

                if (isDismissed && dismissDirection == DismissDirection.EndToStart
                ) {
                    val scope = rememberCoroutineScope()
                    scope.launch {
                        delay(300)
                        GlobalScope.launch(Dispatchers.IO) {
                            sharedViewModel.drop(card.create_date)
                        }
                        println(state)
                        if (state.contains(card)){
                            state = state.toMutableList().also{list-> list.remove(card)}
                            notes.toMutableList().remove(card)
                        }
                        println(state)
                    }
                }


                var itemAppeared by remember { mutableStateOf(!columnAppeared) }
                LaunchedEffect(Unit) {
                    itemAppeared = true
                }
                AnimatedVisibility(
                    visible = itemAppeared && !isDismissed,
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 300,
                        )
                    ),
                    enter = expandVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    )
                ) {
                    SwipeToDismiss(
                        state = dismissState,
                        dismissThresholds = { FractionalThreshold(0.2f) },
                        background = {
                            val color = when (dismissState.dismissDirection) {
                                DismissDirection.StartToEnd -> Color.Transparent
                                DismissDirection.EndToStart -> MaterialTheme.colors.background
                                null -> Color.Transparent
                            }
                            Box(
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(0.dp, 8.dp, 0.dp, 3.dp)
                                    .background(color)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.surface,
                                    modifier = Modifier.align(Alignment.CenterEnd)
                                )
                            }
                        },
                        dismissContent = {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(16.dp, 8.dp, 16.dp, 3.dp),
                                backgroundColor = MaterialTheme.colors.secondary,
                                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                                elevation = 0.dp,
                                content = { CardContent(note = card) },
                                onClick = {
                                    navHostController.navigate(route = "add/" + card.id)
                                }
                            )
                        },
                        directions = setOf(DismissDirection.EndToStart)
                    )
                }
            }
        }
    }
}
