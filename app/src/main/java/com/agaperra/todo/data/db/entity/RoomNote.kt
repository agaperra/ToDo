package com.agaperra.todo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agaperra.todo.data.model.Levels

@Entity
data class RoomNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    //var title: String?,
    var create_date: String,
    var edit_date: String,
    var note: String?,
    var level: Levels
)