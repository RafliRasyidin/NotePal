package com.rasyidin.notepal.data.repository

import com.rasyidin.notepal.data.local.entities.note.NoteDao
import com.rasyidin.notepal.data.local.entities.note_content.NoteContentDao
import com.rasyidin.notepal.data.local.entities.tag.TagDao
import com.rasyidin.notepal.data.local.entities.task.TaskDao
import com.rasyidin.notepal.domain.model.detail_note.DetailNote
import com.rasyidin.notepal.domain.model.detail_note.NoteContent
import com.rasyidin.notepal.domain.model.detail_note.Tag
import com.rasyidin.notepal.domain.model.detail_note.Task
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val noteContentDao: NoteContentDao,
    private val tagDao: TagDao,
    private val taskDao: TaskDao
) : NoteRepository {
    override suspend fun insertNote(detailNote: DetailNote) {
        noteDao.insert(detailNote.toEntity())
    }

    override suspend fun deleteNote(detailNote: DetailNote) {
        noteDao.delete(detailNote.toEntity())
    }

    override suspend fun updateNote(detailNote: DetailNote) {
        noteDao.update(detailNote.toEntity())
    }

    override suspend fun insertNoteContent(noteContent: NoteContent) {
        noteContentDao.insert(noteContent.toEntity())
    }

    override suspend fun deleteNoteContent(noteContent: NoteContent) {
        noteContentDao.delete(noteContent.toEntity())
    }

    override suspend fun deleteNoteContentByIdNote(idNote: String) {
        noteContentDao.deleteNoteContentByIdNote(idNote)
    }

    override suspend fun updateNoteContent(noteContent: NoteContent) {
        noteContentDao.update(noteContent.toEntity())
    }

    override suspend fun insertNoteTask(task: Task) {
        taskDao.insert(task.toEntity())
    }

    override suspend fun deleteNoteTask(task: Task) {
        taskDao.delete(task.toEntity())
    }

    override suspend fun deleteNoteTaskByIdNote(idNote: String) {
        taskDao.deleteTaskByIdNote(idNote)
    }

    override suspend fun updateNoteTask(task: Task) {
        taskDao.update(task.toEntity())
    }

    override suspend fun insertTagNote(tag: Tag) {
        tagDao.insert(tag.toEntity())
    }

    override suspend fun deleteTagNote(tag: Tag) {
        tagDao.delete(tag.toEntity())
    }

    override suspend fun deleteTagNoteByIdNote(idNote: String) {
        tagDao.deleteTagByIdNote(idNote)
    }

    override suspend fun updateTagNote(tag: Tag) {
        tagDao.update(tag.toEntity())
    }
}