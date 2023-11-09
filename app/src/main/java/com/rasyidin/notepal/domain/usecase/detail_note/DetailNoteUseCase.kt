package com.rasyidin.notepal.domain.usecase.detail_note

import com.rasyidin.notepal.data.repository.NoteRepository
import com.rasyidin.notepal.domain.ResultState
import com.rasyidin.notepal.domain.model.detail_note.ContentType
import com.rasyidin.notepal.domain.model.detail_note.DetailNote
import com.rasyidin.notepal.domain.model.detail_note.NoteContent
import com.rasyidin.notepal.domain.model.detail_note.Task
import com.rasyidin.notepal.util.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface DetailNoteUseCase {
    fun insertNote(detailNote: DetailNote): Flow<ResultState<DetailNote>>
    fun deleteNote(detailNote: DetailNote): Flow<ResultState<Nothing>>
    fun updateNote(detailNote: DetailNote): Flow<ResultState<DetailNote>>
}

class DetailNoteUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository,
) : DetailNoteUseCase {
    override fun insertNote(detailNote: DetailNote): Flow<ResultState<DetailNote>> {
        return channelFlow {
            send(ResultState.Loading())
            val notesContent = mutableListOf<NoteContent>()
            val listTask = mutableListOf<Task>()
            detailNote.contents.forEach { content ->
                if ((content.type == ContentType.FreeText || content.type == ContentType.Image) && content.noteContent != null) {
                    notesContent.add(content.noteContent!!)
                }
                if (content.type == ContentType.Task && content.task != null) {
                    listTask.add(content.task!!)
                }
            }
            val tagsNote = detailNote.tags
            var resultNote = detailNote
            val asyncInsertNotesContent = async {
                if (notesContent.isNotEmpty()) {
                    notesContent.map { noteContent ->
                        noteContent.idNote = detailNote.id
                        noteRepository.upsertNoteContent(noteContent)
                    }
                    resultNote.contents.map { content ->
                        content.noteContent = content.noteContent
                    }
                }
            }
            val asyncInsertTask = async {
                if (listTask.isNotEmpty()) {
                    listTask.forEach { taskNote ->
                        taskNote.idNote = detailNote.id
                        noteRepository.upsertNoteTask(taskNote)
                    }
                    resultNote.contents.map { content ->
                        content.task = content.task
                    }
                }
            }
            val asyncInsertTags = async {
                if (tagsNote.isNotEmpty()) {
                    tagsNote.forEach { tagNote ->
                        tagNote.idNote = detailNote.id
                        noteRepository.upsertTagNote(tagNote)
                    }
                    resultNote.tags = tagsNote
                }
            }
            asyncInsertNotesContent.await()
            asyncInsertTask.await()
            asyncInsertTags.await()
            resultNote = detailNote.copy(createdAt = DateUtils.currentDateTime())
            noteRepository.upsertNote(resultNote)
            send(ResultState.Success(resultNote))
        }.catch { e ->
            emit(ResultState.Error(e))
        }.flowOn(Dispatchers.IO)
    }

    override fun deleteNote(detailNote: DetailNote): Flow<ResultState<Nothing>> {
        return channelFlow {
            send(ResultState.Loading())
            val notesContent = mutableListOf<NoteContent>()
            val listTask = mutableListOf<Task>()
            detailNote.contents.forEach { content ->
                if ((content.type == ContentType.FreeText || content.type == ContentType.Image) && content.noteContent != null) {
                    notesContent.add(content.noteContent!!)
                }
                if (content.type == ContentType.Task && content.task != null) {
                    listTask.add(content.task!!)
                }
            }
            noteRepository.deleteNote(detailNote)
            val operationDeleteNotesContent = async {
                if (notesContent.isNotEmpty()) {
                    noteRepository.deleteNoteContentByIdNote(detailNote.id)
                }
            }
            val operationDeleteTasksNote = async {
                if (listTask.isNotEmpty()) {
                    noteRepository.deleteNoteTaskByIdNote(detailNote.id)
                }
            }
            val operationDeleteTagsNote = async {
                if (detailNote.tags.isNotEmpty()) {
                    noteRepository.deleteTagNoteByIdNote(detailNote.id)
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

    override fun updateNote(detailNote: DetailNote): Flow<ResultState<DetailNote>> {
        return channelFlow {
            send(ResultState.Loading())
            val notesContent = mutableListOf<NoteContent>()
            val listTask = mutableListOf<Task>()
            detailNote.contents.forEach { content ->
                if ((content.type == ContentType.FreeText || content.type == ContentType.Image) && content.noteContent != null) {
                    notesContent.add(content.noteContent!!)
                }
                if (content.type == ContentType.Task && content.task != null) {
                    listTask.add(content.task!!)
                }
            }
            val tagsNote = detailNote.tags
            val operationUpdateNotesContent = async {
                if (notesContent.isNotEmpty()) {
                    notesContent.forEach { content ->
                        noteRepository.upsertNoteContent(content)
                    }
                }
            }
            val operationUpdateTasksNote = async {
                if (listTask.isNotEmpty()) {
                    listTask.forEach { taskNote ->
                        noteRepository.upsertNoteTask(taskNote)
                    }
                }
            }
            val operationUpdateTagsNote = async {
                if (tagsNote.isNotEmpty()) {
                    tagsNote.map { tagNote ->
                        noteRepository.upsertTagNote(tagNote)
                    }
                }
            }
            operationUpdateNotesContent.await()
            operationUpdateTasksNote.await()
            operationUpdateTagsNote.await()
            val resultNote = detailNote.copy(updatedAt = DateUtils.currentDateTime())
            noteRepository.updateNote(resultNote)
            send(ResultState.Success(resultNote))
        }.catch { e ->
            emit(ResultState.Error(e))
        }.flowOn(Dispatchers.IO)
    }
}