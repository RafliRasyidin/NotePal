package com.rasyidin.notepal.data.repository

import com.rasyidin.notepal.domain.model.detail_note.Note
import com.rasyidin.notepal.domain.model.detail_note.NoteContent
import com.rasyidin.notepal.domain.model.detail_note.Tag
import com.rasyidin.notepal.domain.model.detail_note.Task

interface NoteRepository {

    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun insertNoteContent(noteContent: NoteContent)
    suspend fun deleteNoteContent(noteContent: NoteContent)
    suspend fun deleteNoteContentByIdNote(idNote: String)
    suspend fun updateNoteContent(noteContent: NoteContent)
    suspend fun insertNoteTask(task: Task)
    suspend fun deleteNoteTask(task: Task)
    suspend fun deleteNoteTaskByIdNote(idNote: String)
    suspend fun updateNoteTask(task: Task)
    suspend fun insertTagNote(tag: Tag)
    suspend fun deleteTagNote(tag: Tag)
    suspend fun deleteTagNoteByIdNote(idNote: String)
    suspend fun updateTagNote(tag: Tag)
}