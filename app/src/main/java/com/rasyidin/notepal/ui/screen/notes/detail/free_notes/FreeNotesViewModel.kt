package com.rasyidin.notepal.ui.screen.notes.detail.free_notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasyidin.notepal.domain.ResultState
import com.rasyidin.notepal.domain.model.detail_note.Note
import com.rasyidin.notepal.domain.onSuccess
import com.rasyidin.notepal.domain.usecase.detail_note.DetailNoteUseCase
import com.rasyidin.notepal.ui.screen.notes.detail.DetailNoteEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FreeNotesViewModel @Inject constructor(
    private val useCase: DetailNoteUseCase
) : ViewModel() {

    var uiState by mutableStateOf(Note())
        private set

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
            is DetailNoteEvent.OnContentChange -> uiState = uiState.copy(notesContent = event.notesContent)
            is DetailNoteEvent.OnFinished -> uiState = uiState.copy(finished = event.finished)
            is DetailNoteEvent.OnPinned -> uiState = uiState.copy(pinned = uiState.pinned)
            is DetailNoteEvent.OnReminderChange -> uiState = uiState.copy(reminder = event.reminder)
            is DetailNoteEvent.OnTagChange -> uiState = uiState.copy(tags = event.tags)
            is DetailNoteEvent.OnTaskChange -> uiState = uiState.copy(listTask = event.tasks)
            is DetailNoteEvent.OnTitleChange -> uiState = uiState.copy(title = event.title)
            DetailNoteEvent.DeleteNote -> deleteNote()
            DetailNoteEvent.HideSheet -> uiState = uiState.copy(showSheetMenuExtra = false)
            DetailNoteEvent.ShowSheetMenuExtra -> uiState = uiState.copy(showSheetMenuExtra = true)
            DetailNoteEvent.InsertNote -> insertNote()
            DetailNoteEvent.UpdateNote -> deleteNote()
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