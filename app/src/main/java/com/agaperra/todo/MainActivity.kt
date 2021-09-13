package com.agaperra.todo

import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.agaperra.todo.ui.theme.ToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Row() {
                            Greeting("monster")
                        }
                        Row() {
                            Greeting("Android")
                            Greeting("AGAPERRA")
                        }
                        MyComposable()
                    }


                }
            }
        }
    }
}

