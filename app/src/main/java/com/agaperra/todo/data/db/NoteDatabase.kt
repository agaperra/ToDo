package com.agaperra.todo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agaperra.todo.data.db.dao.NoteDao
import com.agaperra.todo.data.db.entity.RoomNote

@Database(
    entities = [RoomNote::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}