package com.agaperra.todo.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agaperra.todo.data.db.NoteDatabaseHelper
import com.agaperra.todo.data.db.entity.RoomNote
import com.agaperra.todo.data.model.Levels
import com.agaperra.todo.utils.Constants.notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: NoteDatabaseHelper
) : ViewModel() {

    var readAllNote = repository.readAllNotes

    init {
        GlobalScope.launch {
            val items = repository.readAllNotes.value
            viewModelScope.launch {
                notes = items ?: listOf()
                readAllNote = repository.readAllNotes
            }
        }
    }

    private val _isFirstLaunch = MutableStateFlow(value = true)
    val isFirstLaunch: StateFlow<Boolean> = _isFirstLaunch


    suspend fun drop(date: String) = repository.drop(date)
    suspend fun getCount(): Int = repository.getCount()
    fun getDetails(id: Int) = repository.getDataById(id)

    suspend fun saveNoteToDB(
        //title: String?,
        create_date: String,
        edit_date: String,
        note: String?,
        level: Levels
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(RoomNote(null, create_date, edit_date, note, level))
        }
    }

    suspend fun updateNote(
        id : Int?,
        //title: String?,
        create_date: String,
        edit_date: String,
        note: String?,
        level: Levels
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update( id,create_date, edit_date, note, level)
        }
    }


}