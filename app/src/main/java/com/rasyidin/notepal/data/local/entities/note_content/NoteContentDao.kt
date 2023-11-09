package com.rasyidin.notepal.data.local.entities.note_content

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteContentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(noteContent: NoteContentEntity)

    @Delete
    suspend fun delete(noteContent: NoteContentEntity)

    @Query("DELETE FROM note_content WHERE idNote = :idNote")
    suspend fun deleteNoteContentByIdNote(idNote: String)

    @Update
    suspend fun update(noteContent: NoteContentEntity)

    @Query("SELECT * FROM note_content WHERE idNote = :idNote ORDER BY orderContent")
    suspend fun getNotesContentByIdNote(idNote: String): List<NoteContentEntity>
}