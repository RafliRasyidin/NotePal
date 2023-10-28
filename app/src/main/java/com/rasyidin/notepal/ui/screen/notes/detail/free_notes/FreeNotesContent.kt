package com.rasyidin.notepal.ui.screen.notes.detail.free_notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rasyidin.notepal.R
import com.rasyidin.notepal.domain.model.add_notes.ColorNote
import com.rasyidin.notepal.domain.model.add_notes.Label
import com.rasyidin.notepal.domain.model.add_notes.MenuExtra
import com.rasyidin.notepal.ui.component.Chip
import com.rasyidin.notepal.ui.component.LineSeparator
import com.rasyidin.notepal.ui.component.TextFieldNotes
import com.rasyidin.notepal.ui.component.TextFieldTitleNotes
import com.rasyidin.notepal.ui.component.ToolbarNotes
import com.rasyidin.notepal.ui.screen.notes.detail.DragHandleSheetExtrasMenuNote
import com.rasyidin.notepal.ui.screen.notes.detail.SheetExtrasMenuNoteContent
import com.rasyidin.notepal.ui.theme.NotePalTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreeNotesContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onBookmarkClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    onMenuSheetClick: (MenuExtra) -> Unit = {},
    onColorNoteClick: (ColorNote) -> Unit = {},
) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var showSheet by remember { mutableStateOf(false) }
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    if (showSheet) {
        ModalBottomSheet(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
            onDismissRequest = { showSheet = false },
            sheetState = state,
            dragHandle = {
                DragHandleSheetExtrasMenuNote() {
                    scope.launch {
                        state.hide()
                    }.invokeOnCompletion {
                        if (!state.isVisible) {
                            showSheet = false
                        }
                    }
                }
            }
        ) {
            SheetExtrasMenuNoteContent(
                onDeleteClick = onDeleteClick,
                onMenuClick = onMenuSheetClick,
                onColorClick = onColorNoteClick
            )
        }
    }
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            ToolbarNotes(onBackClick = onBackClick)
            Column(
                modifier = Modifier
                    .weight(1F)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                TextFieldTitleNotes(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    title = title,
                    hint = stringResource(id = R.string.title_here),
                    onValueChange = { newText -> title = newText }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldNotes(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = body,
                    hint = stringResource(id = R.string.hint_notes),
                    onTextChange = { newText -> body = newText }
                )
                Spacer(modifier = Modifier.height(24.dp))
                LineSeparator(modifier = Modifier.padding(horizontal = 16.dp))
                Spacer(modifier = Modifier.height(24.dp))
                Reminder(date = "15/07/2021, 18:30")
                Spacer(modifier = Modifier.height(24.dp))
                Labels(
                    labels = listOf(
                        Label("1", "Important"),
                        Label("1", "Top Priority"),
                        Label("1", "Top Priority"),
                        Label("1", "Top Priority"),
                        Label("1", "Top Priority"),
                        Label("1", "Top Priority"),
                    ),
                    onLabelClick = {

                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            LineSeparator()
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.last_edited_at, "19.30"),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .weight(1F)
                        .padding(start = 16.dp)
                )
                IconButton(
                    onClick = onSearchClick,
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = onBookmarkClick,
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bookmark_outlined),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.primary)
                        .clickable { showSheet = true }
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dots_horizontal),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Labels(
    modifier: Modifier = Modifier,
    labels: List<Label>,
    onLabelClick: (Label) -> Unit,
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        labels.forEach { label ->
            Chip(
                text = label.text,
                onClick = {
                    onLabelClick(label)
                }
            )
        }
    }
}

@Composable
private fun Reminder(
    modifier: Modifier = Modifier,
    date: String,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = stringResource(id = R.string.reminder_set, date),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewFreeNotesContent() {
    NotePalTheme {
        FreeNotesContent()
    }
}