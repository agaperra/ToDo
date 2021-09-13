package com.agaperra.todo

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.agaperra.todo.ui.theme.ToDoTheme

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToDoTheme {
        Greeting("Android")
    }
}

@Composable
fun MyComposable(){
    var myValue by remember{ mutableStateOf(false) }
    Log.d("Recomposition", "MyComposable")
    Button(onClick={myValue = !myValue}){
        Text(text = "$myValue")
        Log.d("Recomposition", "Button Content Lambda")
    }
}