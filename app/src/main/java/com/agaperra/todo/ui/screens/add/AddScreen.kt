package com.agaperra.todo.ui.screens.add

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agaperra.todo.R
import com.agaperra.todo.data.model.Levels
import com.agaperra.todo.ui.theme.Color
import dagger.hilt.android.internal.managers.FragmentComponentManager.findActivity

@ExperimentalComposeUiApi
@Preview
@Composable
fun AddScreen(onClick: () -> Unit = {}) {
    MaterialTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp, 0.dp, 30.dp, 0.dp)
        ) {


            var textState by remember { mutableStateOf(TextFieldValue()) }

            val focusRequester = remember { FocusRequester() }
            val keyboardController = LocalSoftwareKeyboardController.current

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 30.dp, 0.dp, 30.dp)
                    .focusRequester(focusRequester),
                value = textState,
                onValueChange = { newText -> textState = newText },
                label = {
                    Text(
                        stringResource(id = R.string.title),
                        color = MaterialTheme.colors.primaryVariant
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primaryVariant,
                    unfocusedBorderColor = MaterialTheme.colors.background,
                    textColor = MaterialTheme.colors.primaryVariant
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
            )


            var expanded by remember { mutableStateOf(false) }
            var levelText by remember { mutableStateOf(Levels.LOW.toString()) }
            var levelColor by remember { mutableStateOf(Color.green) }

            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = MaterialTheme.colors.surface,
                border = BorderStroke(1.dp, MaterialTheme.colors.background)
            ) {
                Row(
                    Modifier
                        .clickable {
                            expanded = !expanded
                        }
                        .padding(8.dp)
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Column(
                        modifier =
                        Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(8.dp, 0.dp, 0.dp, 0.dp),
                            text = levelText,
                            color = MaterialTheme.colors.primaryVariant,
                        )
                    }

                    Column(
                        modifier = Modifier.padding(8.dp, 2.dp, 8.dp, 2.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Canvas(modifier = Modifier.size(20.dp), onDraw = {
                            drawCircle(color = levelColor)
                        })
                    }
                    Column(
                        modifier =
                        Modifier
                            .wrapContentSize()
                            .padding(8.dp, 0.dp, 0.dp, 0.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            "",
                            tint = MaterialTheme.colors.primaryVariant,
                        )
                    }

                    val levelList = listOf(
                        Pair(Levels.HIGH.toString(), Color.red),
                        Pair(Levels.MEDIUM.toString(), Color.yellow),
                        Pair(Levels.LOW.toString(), Color.green)
                    )


                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        },
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(8.dp, 8.dp, 8.dp, 8.dp)
                    ) {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = MaterialTheme.colors.surface,
                            border = BorderStroke(1.dp, MaterialTheme.colors.background)
                        ) {
                            Column(
                                modifier =
                                Modifier
                                    .fillMaxSize()
                            ) {
                                levelList.forEach { level ->
                                    DropdownMenuItem(
                                        onClick = {
                                            expanded = false
                                            levelText = level.first
                                            levelColor = level.second
                                        },
                                        modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp, 0.dp, 8.dp, 0.dp)
                                    ) {
                                        Row(
                                            modifier =
                                            Modifier
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Start
                                        ) {

                                            Column(
                                                modifier =
                                                Modifier.weight(1f),
                                                horizontalAlignment = Alignment.Start
                                            ) {
                                                val isSelected = level.first == levelText
                                                val style = if (isSelected) {
                                                    MaterialTheme.typography.body1.copy(
                                                        fontWeight = FontWeight.Bold,
                                                        color = MaterialTheme.colors.secondary
                                                    )
                                                } else {
                                                    MaterialTheme.typography.body1.copy(
                                                        fontWeight = FontWeight.Normal,
                                                        color = MaterialTheme.colors.onSurface
                                                    )
                                                }
                                                Text(
                                                    level.first,
                                                    color = MaterialTheme.colors.primaryVariant,
                                                    style = style
                                                )
                                            }

                                            Column(
                                                modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                                                horizontalAlignment = Alignment.End,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Canvas(modifier = Modifier.size(20.dp), onDraw = {
                                                    drawCircle(color = level.second)
                                                })

                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }


            var textState1 by remember { mutableStateOf(TextFieldValue()) }

            val focusRequester1 = remember { FocusRequester() }
            val keyboardController1 = LocalSoftwareKeyboardController.current

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(0.dp, 30.dp, 0.dp, 10.dp)
                    .focusRequester(focusRequester1),
                value = textState1,
                onValueChange = { newText -> textState1 = newText },
                label = {
                    Text(
                        stringResource(id = R.string.note),
                        color = MaterialTheme.colors.primaryVariant
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primaryVariant,
                    unfocusedBorderColor = MaterialTheme.colors.background,
                    textColor = MaterialTheme.colors.primaryVariant
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController1?.hide()
                    }
                )
            )

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
                        onClick =  onClick ,
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.primaryVariant,
                    ) {
                        Icon(Icons.Filled.ArrowBack, "")
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    val onClickDone = { }
                    FloatingActionButton(
                        onClick = onClickDone,
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.primaryVariant,
                    ) {
                        Icon(Icons.Filled.Done, "")
                    }
                }
            }
        }
    }
}



