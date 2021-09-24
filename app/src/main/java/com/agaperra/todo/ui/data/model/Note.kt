package com.agaperra.todo.ui.data.model

data class Note(
    var id: Int,
    var title: String?,
    var level: Levels,
    var create_date: String,
    var edit_date: String,
    var note: String?
)