package com.rasyidin.notepal.data.local.entities.note

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Query("SELECT * FROM note WHERE id = :idNote")
    fun getNoteById(idNote: String): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteEntity: NoteEntity)

    @Update
    suspend fun update(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    /*@Query("""
        SELECT
            a.id as idNote,
            a.backgroundColor,
            a.containerColor,
            a.onContainerColor,
            a.createdAt,
            a.finished,
            a.pinned,
            a.reminder,
            a.title,
            a.updatedAt,
            b.id as idContent,
            b.content as contentNote,
            b.orderContent as orderContent,
            b.content_type
        FROM note AS a
        LEFT JOIN note_content AS b ON a.id = b.idNote
        LEFT JOIN task AS c ON a.id = c.id
    """)
    suspend fun getNotes()*/

}