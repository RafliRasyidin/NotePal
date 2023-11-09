package com.rasyidin.notepal.ui.screen.notes.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasyidin.notepal.data.local.entities.note_content.NoteContentType
import com.rasyidin.notepal.domain.ResultState
import com.rasyidin.notepal.domain.model.detail_note.Content
import com.rasyidin.notepal.domain.model.detail_note.ContentType
import com.rasyidin.notepal.domain.model.detail_note.DetailNote
import com.rasyidin.notepal.domain.model.detail_note.NoteContent
import com.rasyidin.notepal.domain.model.detail_note.Tag
import com.rasyidin.notepal.domain.model.detail_note.Task
import com.rasyidin.notepal.domain.onSuccess
import com.rasyidin.notepal.domain.usecase.detail_note.DetailNoteUseCase
import com.rasyidin.notepal.util.IdGeneratorUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailNotesViewModel @Inject constructor(
    private val useCase: DetailNoteUseCase
) : ViewModel() {

    var uiState by mutableStateOf(DetailNote())
    private var contents = mutableStateListOf<Content>()
    private var tags = mutableStateListOf<Tag>()

    private val _deleteNoteState: Channel<ResultState<Nothing>> = Channel()
    val deleteNoteState = _deleteNoteState.receiveAsFlow()

    fun setEvent(event: DetailNoteEvent) {
        when (event) {
            is DetailNoteEvent.OnColorChange -> {
                uiState = uiState.copy(
                    backgroundColor = event.backgroundColor.toArgb(),
                    containerColor = event.containerColor.toArgb(),
                    onContainerColor = event.onContainerColor.toArgb()
                )
            }
            is DetailNoteEvent.OnContentChange -> {
                contents = event.notesContent.toMutableStateList()
                uiState = uiState.copy(contents = contents)
            }
            is DetailNoteEvent.OnFinished -> {
                val isFinished = !event.finished
                uiState = uiState.copy(finished = isFinished)
            }
            is DetailNoteEvent.OnPinned -> {
                uiState = uiState.copy(pinned = uiState.pinned)
            }
            is DetailNoteEvent.OnReminderChange -> {
                uiState = uiState.copy(reminder = event.reminder)
            }
            is DetailNoteEvent.OnTagChange -> {
                tags = event.tags.toMutableStateList()
                uiState = uiState.copy(tags = tags)
            }
            is DetailNoteEvent.OnTitleChange -> {
                uiState = uiState.copy(title = event.title)
            }
            DetailNoteEvent.OnFirstTime -> {
                val idNote = IdGeneratorUtils.generateId(IdGeneratorUtils.PREFIX_ID_NOTE)
                val idContent = IdGeneratorUtils.generateId(IdGeneratorUtils.PREFIX_ID_CONTENT_FREE_TEXT, 25)
                val tempContents = uiState.contents.toMutableList()
                val newContent = Content(
                    type = ContentType.FreeText,
                    noteContent = NoteContent(
                        id = idContent,
                        idNote = uiState.id,
                        value = "",
                        orderContent = uiState.contents.size + 1,
                        type = NoteContentType.Image
                    )
                )
                tempContents.add(newContent)
                contents = tempContents.toMutableStateList()
                uiState = uiState.copy(
                    id =  idNote,
                    contents = contents
                )
                insertNote()
            }
            DetailNoteEvent.DeleteNote -> deleteNote()
            DetailNoteEvent.HideSheet -> uiState = uiState.copy(showSheetMenuExtra = false)
            DetailNoteEvent.ShowSheetMenuExtra -> uiState = uiState.copy(showSheetMenuExtra = true)
            DetailNoteEvent.UpdateNote -> updateNote()
            DetailNoteEvent.AddChecklist -> {
                val idContent = IdGeneratorUtils.generateId(IdGeneratorUtils.PREFIX_ID_CONTENT_TASK, 25)
                val tempContents = uiState.contents.toMutableList()
                val newContent = Content(
                    type = ContentType.Task,
                    noteContent = null,
                    task = Task(
                        id = idContent,
                        idNote = uiState.id,
                        order = uiState.contents.size + 1,
                        name = ""
                    )
                )
                tempContents.add(newContent)
                contents = tempContents.toMutableStateList()
                uiState = uiState.copy(contents = contents)
            }
            DetailNoteEvent.AddFreeText -> {
                val idContent = IdGeneratorUtils.generateId(IdGeneratorUtils.PREFIX_ID_CONTENT_FREE_TEXT, 25)
                val tempContents = uiState.contents.toMutableList()
                val newContent = Content(
                    type = ContentType.FreeText,
                    noteContent = NoteContent(
                        id = idContent,
                        idNote = uiState.id,
                        value = "",
                        orderContent = uiState.contents.size + 1,
                        type = NoteContentType.Image
                    )
                )
                tempContents.add(newContent)
                contents = tempContents.toMutableStateList()
                uiState = uiState.copy(contents = contents)
            }
            DetailNoteEvent.AddImage -> {
                val idContent = IdGeneratorUtils.generateId(IdGeneratorUtils.PREFIX_ID_CONTENT_IMAGE, 25)
                val tempContents = uiState.contents.toMutableList()
                val newContent = Content(
                    type = ContentType.Image,
                    noteContent = NoteContent(
                        id = idContent,
                        idNote = uiState.id,
                        value = "",
                        orderContent = uiState.contents.size + 1,
                        type = NoteContentType.Image
                    ),
                )
                tempContents.add(newContent)
                contents = tempContents.toMutableStateList()
                uiState = uiState.copy(contents = tempContents)
            }
        }
    }

    private fun insertNote() {
        viewModelScope.launch {
            useCase.insertNote(uiState).collect { result ->
                result.onSuccess { note ->
                    note?.let {
                        uiState = note
                    }
                }
            }
        }
    }

    private fun deleteNote() {
        viewModelScope.launch {
            useCase.deleteNote(uiState).collect { result ->
                _deleteNoteState.send(result)
            }
        }
    }

    private fun updateNote() {
        viewModelScope.launch {
            Log.d("UpdateNote", uiState.toString())
            useCase.updateNote(uiState).collect { result ->
                result.onSuccess { note ->
                    note?.let {
                        uiState = note
                    }
                }
            }
        }
    }
}