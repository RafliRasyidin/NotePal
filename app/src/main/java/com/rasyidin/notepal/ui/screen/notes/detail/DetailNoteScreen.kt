package com.rasyidin.notepal.ui.screen.notes.detail

import androidx.activity.compose.BackHandler
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.rasyidin.notepal.R
import com.rasyidin.notepal.domain.model.add_notes.ColorIdGreen
import com.rasyidin.notepal.domain.model.add_notes.ColorIdPurple
import com.rasyidin.notepal.domain.model.add_notes.ColorIdRed
import com.rasyidin.notepal.domain.model.add_notes.ColorIdSalmon
import com.rasyidin.notepal.domain.model.add_notes.ColorIdWhite
import com.rasyidin.notepal.domain.model.add_notes.ColorIdYellow
import com.rasyidin.notepal.domain.onSuccess
import com.rasyidin.notepal.ui.theme.notesGreenContainer
import com.rasyidin.notepal.ui.theme.notesGreenDark
import com.rasyidin.notepal.ui.theme.notesPurpleContainer
import com.rasyidin.notepal.ui.theme.notesPurpleDark
import com.rasyidin.notepal.ui.theme.notesRedContainer
import com.rasyidin.notepal.ui.theme.notesRedDark
import com.rasyidin.notepal.ui.theme.notesSalmonContainer
import com.rasyidin.notepal.ui.theme.notesSalmonDark
import com.rasyidin.notepal.ui.theme.notesWhiteContainer
import com.rasyidin.notepal.ui.theme.notesYellowContainer
import com.rasyidin.notepal.ui.theme.notesYellowDark
import com.rasyidin.notepal.util.showShortToast
import kotlinx.coroutines.delay

@Composable
fun DetailNoteScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailNotesViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background
    val containerColor = MaterialTheme.colorScheme.outline
    val context = LocalContext.current
    BackHandler {
        if (viewModel.uiState.title.isNotEmpty() || viewModel.uiState.contents.isNotEmpty()) {
            viewModel.setEvent(DetailNoteEvent.UpdateNote)
        }
        onBackClick()
    }
    LaunchedEffect(Unit) {
        viewModel.uiState = viewModel.uiState.copy(
            backgroundColor = backgroundColor.toArgb(),
            containerColor = containerColor.toArgb(),
            onContainerColor = onBackgroundColor.toArgb()
        )
        viewModel.setEvent(DetailNoteEvent.OnFirstTime)
        while (true) {
            delay(5000)
            viewModel.setEvent(DetailNoteEvent.UpdateNote)
        }
    }
    LaunchedEffect(Unit) {
        viewModel.deleteNoteState.collect { result ->
            result.onSuccess {
                context.showShortToast(context.getString(R.string.delete_success))
                onBackClick()
            }
        }
    }
    DetailNotesContent(
        modifier = modifier,
        uiState = viewModel.uiState,
        onBackClick = onBackClick,
        onBookmarkClick = { isPinned -> viewModel.setEvent(DetailNoteEvent.OnPinned(isPinned)) },
        onSearchClick = {},
        onDeleteClick = { viewModel.setEvent(DetailNoteEvent.DeleteNote) },
        onMenuSheetClick = { menu ->
            when (menu.title.toString()) {
                context.getString(R.string.set_reminder) -> Unit
                context.getString(R.string.change_note_type) -> Unit
                context.getString(R.string.add_label) -> Unit
                context.getString(R.string.mark_as_finished) -> {
                    viewModel.setEvent(DetailNoteEvent.OnFinished(viewModel.uiState.finished))
                }
            }
        },
        onColorNoteClick = { colorNote ->
            val containerColor: Color
            val onContainerColor: Color
            when (colorNote.id) {
                ColorIdGreen -> {
                    containerColor = notesGreenContainer
                    onContainerColor = notesGreenDark
                }
                ColorIdPurple -> {
                    containerColor = notesPurpleContainer
                    onContainerColor = notesPurpleDark
                }
                ColorIdRed -> {
                    containerColor = notesRedContainer
                    onContainerColor = notesRedDark
                }
                ColorIdWhite -> {
                    containerColor = notesWhiteContainer
                    onContainerColor = onBackgroundColor
                }
                ColorIdSalmon -> {
                    containerColor = notesSalmonContainer
                    onContainerColor = notesSalmonDark
                }
                ColorIdYellow -> {
                    containerColor = notesYellowContainer
                    onContainerColor = notesYellowDark
                }
                else -> {
                    containerColor = notesPurpleContainer
                    onContainerColor = notesPurpleContainer
                }
            }
            viewModel.setEvent(
                DetailNoteEvent.OnColorChange(
                    backgroundColor = colorNote.color,
                    containerColor = containerColor,
                    onContainerColor = onContainerColor
                )
            )
        },
        onAddFreeText = { viewModel.setEvent(DetailNoteEvent.AddFreeText) },
        onAddChecklist = { viewModel.setEvent(DetailNoteEvent.AddChecklist) },
        onAddImage = { viewModel.setEvent(DetailNoteEvent.AddImage) },
        onTitleChange = { title -> viewModel.setEvent(DetailNoteEvent.OnTitleChange(title)) },
        onContentChange = { contents -> viewModel.setEvent(DetailNoteEvent.OnContentChange(contents)) }
    )
}