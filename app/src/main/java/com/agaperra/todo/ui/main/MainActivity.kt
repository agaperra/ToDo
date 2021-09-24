package com.agaperra.todo.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agaperra.todo.R
import com.agaperra.todo.ui.data.DataProvider
import com.agaperra.todo.ui.data.model.Note
import com.agaperra.todo.ui.theme.ToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                DefaultPreview()
            }
        }
    }
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
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToDoTheme {
        Column() {
            Surface() {
                TopAppBar(
                    modifier = Modifier.wrapContentSize(),
                    title = { Text(text = "AppBar", ) },
                    backgroundColor = MaterialTheme.colors.background,
                    elevation = 2.dp,
                    navigationIcon = {
                        Image(
                            painterResource(R.drawable.ic_menu),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.wrapContentSize(),
                        )
                    }
                )
            }
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                color = MaterialTheme.colors.surface
            ) {
                Content(DataProvider.notesList)
                Row(
                    modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 8.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    val onClick = { /* Do something */ }
                    FloatingActionButton(
                        onClick = onClick,
                        backgroundColor = Color.Gray,
                        contentColor = Color.Black,
                    ) {
                        Icon(Icons.Filled.Add, "")
                    }
                }
            }
        }
    }
}

