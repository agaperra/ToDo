package com.agaperra.todo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agaperra.todo.data.db.entity.RoomNote
import com.agaperra.todo.data.model.Levels

@Dao
interface NoteDao {
    @Query("SELECT * FROM RoomNote ORDER BY edit_date desc")
    fun all(): LiveData<List<RoomNote>>

    @Query("SELECT * FROM RoomNote WHERE id=:id")
    fun getDataById(id: Int): RoomNote

    @Query("DELETE FROM RoomNote WHERE create_date LIKE :date")
    suspend fun drop(date: String)

    @Query("UPDATE RoomNote SET id=:id,edit_date=:edit_date,note=:note, level=:level WHERE create_date=:create_date")
    suspend fun update(id: Int?,  create_date: String, edit_date: String, note: String?, level: Levels)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RoomNote)

    @Query("SELECT COUNT (*) FROM RoomNote")
    suspend fun getCount(): Int

}