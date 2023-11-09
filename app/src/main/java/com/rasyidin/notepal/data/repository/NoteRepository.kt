package com.rasyidin.notepal.data.repository

import com.rasyidin.notepal.domain.model.detail_note.DetailNote
import com.rasyidin.notepal.domain.model.detail_note.NoteContent
import com.rasyidin.notepal.domain.model.detail_note.Tag
import com.rasyidin.notepal.domain.model.detail_note.Task

interface NoteRepository {

    suspend fun upsertNote(detailNote: DetailNote)
    suspend fun deleteNote(detailNote: DetailNote)
    suspend fun updateNote(detailNote: DetailNote)
    suspend fun upsertNoteContent(noteContent: NoteContent)
    suspend fun deleteNoteContent(noteContent: NoteContent)
    suspend fun deleteNoteContentByIdNote(idNote: String)
    suspend fun updateNoteContent(noteContent: NoteContent)
    suspend fun upsertNoteTask(task: Task)
    suspend fun deleteNoteTask(task: Task)
    suspend fun deleteNoteTaskByIdNote(idNote: String)
    suspend fun updateNoteTask(task: Task)
    suspend fun upsertTagNote(tag: Tag)
    suspend fun deleteTagNote(tag: Tag)
    suspend fun deleteTagNoteByIdNote(idNote: String)
    suspend fun updateTagNote(tag: Tag)
}