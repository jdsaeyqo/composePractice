package com.example.noteapp.data

import androidx.compose.runtime.MutableState
import androidx.room.*
import com.example.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("select * from notes_table")
    fun getNotes(): Flow<List<Note>>

    @Query("select * from notes_table where id =:id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("delete from notes_table")
    suspend  fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)
}
