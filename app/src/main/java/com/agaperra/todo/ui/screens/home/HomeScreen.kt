package com.agaperra.todo.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.agaperra.todo.R
import com.agaperra.todo.ui.theme.ToDoTheme
import com.agaperra.todo.ui.viewModel.SharedViewModel
import com.agaperra.todo.ui.card.ActionsRow
import com.agaperra.todo.utils.Constants.ACTION_ITEM_SIZE
import com.agaperra.todo.utils.Constants.CARD_OFFSET
import com.agaperra.todo.utils.Constants.notes
import com.agaperra.todo.ui.card.DraggableCardSimple
import com.agaperra.todo.utils.Toolbar
import com.agaperra.todo.utils.dp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@DelicateCoroutinesApi
@Composable
fun HomeScreen(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    navigateToAddScreen: () -> Unit,
) {
    ToDoTheme {
        Column() {
            Surface() {
                Toolbar(title = R.string.app_name)
            }
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                color = MaterialTheme.colors.surface
            ) {
                Content(sharedViewModel)
                Row(
                    modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 8.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    val onClick = { navigateToAddScreen() }
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

@SuppressLint("CoroutineCreationDuringComposition")
@DelicateCoroutinesApi
@Composable
fun Content(sharedViewModel: SharedViewModel) {

    sharedViewModel.readAllNote.observeForever { notes = it }
    var state  by remember { mutableStateOf(notes) }

    val revealedCardIds = sharedViewModel.revealedCardIdsList.collectAsState()

    GlobalScope.launch {
        val items = sharedViewModel.readAllNote.value
        notes = items ?: listOf()
    }
    if (state.isEmpty()) {
        Image(
            painterResource(R.drawable.ic_box_empty),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.background),
            modifier = Modifier.wrapContentSize()
        )
    } else {
        LazyColumn {
            itemsIndexed(state) { _, card ->
                Box(Modifier.fillMaxWidth()) {
                    ActionsRow(
                        actionIconSize = ACTION_ITEM_SIZE.dp,
                        onDelete = {
                            GlobalScope.launch(Dispatchers.IO) {
                                sharedViewModel.drop(card.create_date)
                            }
                            sharedViewModel.onItemCollapsed(card.id!!)
                            if (state.contains(card)) {
                                state = state.toMutableList().also { list ->
                                    list.remove(card)
                                }
                            }


                        },
                        onEdit = {}
                    )
                    DraggableCardSimple(
                        note = card,
                        isRevealed = revealedCardIds.value.contains(card.id),
                        cardOffset = CARD_OFFSET.dp(),
                        onExpand = { sharedViewModel.onItemExpanded(card.id!!) },
                        onCollapse = { sharedViewModel.onItemCollapsed(card.id!!) },
                    )
                }
            }
        }
    }
}
