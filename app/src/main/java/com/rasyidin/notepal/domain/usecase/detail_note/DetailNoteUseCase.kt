package com.rasyidin.notepal.domain.usecase.detail_note

import com.rasyidin.notepal.data.repository.NoteRepository
import com.rasyidin.notepal.domain.ResultState
import com.rasyidin.notepal.domain.model.detail_note.Note
import com.rasyidin.notepal.util.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface DetailNoteUseCase {
    fun insertNote(note: Note): Flow<ResultState<Note>>
    fun deleteNote(note: Note): Flow<ResultState<Nothing>>
    fun updateNote(note: Note): Flow<ResultState<Note>>
}

class DetailNoteUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
): DetailNoteUseCase {
    override fun insertNote(note: Note): Flow<ResultState<Note>> {
        return channelFlow {
            send(ResultState.Loading())
            val notesContent = note.notesContent
            val listTask = note.listTask
            val tagsNote = note.tags
            var resultNote = note
            val operationInsertNotesContent = async {
                if (notesContent.isNotEmpty()) {
                    notesContent.map { noteContent ->
                        noteContent.idNote = note.id
                        noteRepository.insertNoteContent(noteContent)
                    }
                    resultNote.notesContent = notesContent
                }
            }
            val operationInsertTask = async {
                if (listTask.isNotEmpty()) {
                    listTask.forEach { taskNote ->
                        taskNote.idNote = note.id
                        noteRepository.insertNoteTask(taskNote)
                    }
                    resultNote.listTask = listTask
                }
            }
            val operationInsertTags = async {
                if (tagsNote.isNotEmpty()) {
                    tagsNote.forEach { tagNote ->
                        tagNote.idNote = note.id
                        noteRepository.insertTagNote(tagNote)
                    }
                    resultNote.tags = tagsNote
                }
            }
            operationInsertNotesContent.await()
            operationInsertTask.await()
            operationInsertTags.await()
            resultNote = note.copy(createdAt = DateUtils.currentDateTime())
            noteRepository.insertNote(resultNote)
            send(ResultState.Success(resultNote))
        }.catch { e ->
            emit(ResultState.Error(e))
        }.flowOn(Dispatchers.IO)
    }

    override fun deleteNote(note: Note): Flow<ResultState<Nothing>> {
        return channelFlow {
            send(ResultState.Loading())
            noteRepository.deleteNote(note)
            val operationDeleteNotesContent = async {
                if (note.notesContent.isNotEmpty()) {
                    noteRepository.deleteNoteContentByIdNote(note.id)
                }
            }
            val operationDeleteTasksNote = async {
                if (note.listTask.isNotEmpty()) {
                    noteRepository.deleteNoteTaskByIdNote(note.id)
                }
            }
            val operationDeleteTagsNote = async {
                if (note.tags.isNotEmpty()) {
                    noteRepository.deleteTagNoteByIdNote(note.id)
                }
            }
            operationDeleteNotesContent.await()
            operationDeleteTasksNote.await()
            operationDeleteTagsNote.await()
            send(ResultState.Success(null))
        }.catch { e ->
            emit(ResultState.Error(e))
        }.flowOn(Dispatchers.IO)
    }

    override fun updateNote(note: Note): Flow<ResultState<Note>> {
        return channelFlow {
            send(ResultState.Loading())
            val notesContent = note.notesContent
            val listTask = note.listTask
            val tagsNote = note.tags
            val operationUpdateNotesContent = async {
                if (notesContent.isNotEmpty()) {
                    notesContent.forEach { content ->
                        noteRepository.updateNoteContent(content)
                    }
                }
            }
            val operationUpdateTasksNote = async {
                if (listTask.isNotEmpty()) {
                    listTask.forEach { taskNote ->
                        noteRepository.updateNoteTask(taskNote)
                    }
                }
            }
            val operationUpdateTagsNote = async {
                if (tagsNote.isNotEmpty()) {
                    tagsNote.map { tagNote ->
                        noteRepository.updateTagNote(tagNote)
                    }
                }
            }
            operationUpdateNotesContent.await()
            operationUpdateTasksNote.await()
            operationUpdateTagsNote.await()
            val resultNote = note.copy(updatedAt = DateUtils.currentDateTime())
            noteRepository.updateNote(resultNote)
            send(ResultState.Success(resultNote))
        }.catch { e ->
            emit(ResultState.Error(e))
        }.flowOn(Dispatchers.IO)
    }
}