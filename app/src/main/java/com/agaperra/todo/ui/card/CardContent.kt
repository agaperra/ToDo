package com.agaperra.todo.ui.card

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.agaperra.todo.data.db.entity.RoomNote
import com.agaperra.todo.data.model.Levels
import com.agaperra.todo.ui.theme.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardContent(note: RoomNote) {

    val color =
        when (note.level) {
            Levels.HIGH -> Color.red
            Levels.MEDIUM -> Color.yellow
            Levels.LOW -> Color.green
        }

    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(16.dp, 8.dp, 8.dp, 8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
//            note.title?.let {
//                Text(
//                    text = it,
//                    color = MaterialTheme.colors.primaryVariant,
//                    style = MaterialTheme.typography.h6
//                )
//            }
            note.note?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colors.primaryVariant,
                    style = MaterialTheme.typography.h6
                )
            }
        }
        Column(
            modifier = Modifier.padding(8.dp, 0.dp, 16.dp, 0.dp).wrapContentHeight(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Canvas(modifier = Modifier.size(20.dp), onDraw = {
                drawCircle(color = color)
            })

        }

    }
}