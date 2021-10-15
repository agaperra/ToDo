package com.agaperra.todo.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agaperra.todo.R
import com.agaperra.todo.data.DataProvider
import com.agaperra.todo.data.model.Note
import com.agaperra.todo.ui.main.NoteListItem
import com.agaperra.todo.ui.theme.ToDoTheme
import com.agaperra.todo.utils.Toolbar

@Composable
fun HomeScreen() {
    DefaultPreview()
}

@Composable
fun Content(notes: List<Note>) {
    val note = remember { notes }
    if (note.isEmpty()) {
        Image(
            painterResource(R.drawable.ic_box_empty),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.wrapContentSize()
        )
    } else {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
        ) {
            items(
                items = notes,
                itemContent = {
                    NoteListItem(note = it)
                }
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
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
                Content(DataProvider.notesList1)
                Row(
                    modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 8.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    val onClick = { /* Do something */ }
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