package com.agaperra.todo.data.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agaperra.todo.data.db.dao.NoteDao
import com.agaperra.todo.data.db.entity.RoomNote
import com.agaperra.todo.data.model.Levels
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteDatabaseHelper @Inject constructor(
    private val dao: NoteDao
) : INoteDatabaseHelper {

    override val readAllNotes: LiveData<List<RoomNote>> = dao.all()

    override fun getDataById(id: Int): RoomNote {
        return dao.getDataById(id)
    }

    override suspend fun drop(date: String) = dao.drop(date)

    override suspend fun update(
        id: Int?,
        //title: String?,
        create_date: String,
        edit_date: String,
        note: String?,
        level: Levels
    ) {
        dao.update( id, create_date, edit_date, note, level)
    }

    override suspend fun insert(entity: RoomNote) {
        dao.insert(entity)
    }

    override suspend fun getCount(): Int = dao.getCount()

}

interface INoteDatabaseHelper {
    val readAllNotes: LiveData<List<RoomNote>>
    fun getDataById(id: Int): RoomNote
    suspend fun drop(date: String)
    suspend fun update(id: Int?, create_date: String, edit_date: String, note: String?, level: Levels)
    suspend fun insert(entity: RoomNote)
    suspend fun getCount(): Int
}