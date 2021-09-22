package com.agaperra.todo.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun NewStory(string: String){
    Text(text = string)
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToDoTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            color = MaterialTheme.colors.background
        ) {
            Column() {
                Surface(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(5.dp),
                    color = MaterialTheme.colors.primary
                ) {
                    NewStory(string = "Это новая история о том,")
                }
                Surface(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(15.dp),
                    color = MaterialTheme.colors.primaryVariant
                ) {
                    NewStory(string = "что нужно использовать")
                }
                Surface(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(25.dp),
                    color = MaterialTheme.colors.secondary
                ) {
                    NewStory(string = "столбцы и строки в вашем макете")
                }
            }
        }
    }
}

