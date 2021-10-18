package com.agaperra.todo.ui.screens.add

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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.agaperra.todo.R
import com.agaperra.todo.data.model.Levels
import com.agaperra.todo.ui.theme.Color
import com.agaperra.todo.ui.viewModel.SharedViewModel
import com.agaperra.todo.utils.Constants.simpleDateFormat
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

@DelicateCoroutinesApi
@ExperimentalComposeUiApi
@Composable
fun AddScreen(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    onClick: () -> Unit = {}
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


            var textState by remember { mutableStateOf("") }

            val focusRequester = remember { FocusRequester() }
            val keyboardController = LocalSoftwareKeyboardController.current

            Surface(
                modifier =
                Modifier
                    .fillMaxWidth()
            ) {
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
                        textColor = MaterialTheme.colors.primaryVariant,
                        disabledTextColor = MaterialTheme.colors.primaryVariant,
                        cursorColor = MaterialTheme.colors.primaryVariant
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    )
                )
            }


            var expanded by remember { mutableStateOf(false) }
            var levelText by remember { mutableStateOf(Levels.LOW) }
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
                            text = levelText.toString(),
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
                        Pair(Levels.HIGH, Color.red),
                        Pair(Levels.MEDIUM, Color.yellow),
                        Pair(Levels.LOW, Color.green)
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
                                                    level.first.toString(),
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


            var textState1 by remember { mutableStateOf("") }

            val focusRequester1 = remember { FocusRequester() }
            val keyboardController1 = LocalSoftwareKeyboardController.current

            Surface(
                modifier =
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
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
            }

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
                        onClick = onClick,
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.primaryVariant,
                    ) {
                        Icon(Icons.Filled.ArrowBack, "")
                    }
                }

                Column(horizontalAlignment = Alignment.End) {

                    FloatingActionButton(
                        onClick = {
                            GlobalScope.launch(Dispatchers.IO) {
                                val time = simpleDateFormat.format(Date())
                                sharedViewModel
                                    .saveNoteToDB(
                                        textState,
                                        time,
                                        time,
                                        textState1,
                                        levelText
                                    )

                            }
                            onClick
                        },
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



