package com.rasyidin.notepal.ui.screen.notes.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.rasyidin.notepal.R
import com.rasyidin.notepal.data.local.entities.note_content.NoteContentType
import com.rasyidin.notepal.domain.model.add_notes.ColorNote
import com.rasyidin.notepal.domain.model.add_notes.MenuExtra
import com.rasyidin.notepal.domain.model.detail_note.Content
import com.rasyidin.notepal.domain.model.detail_note.ContentType
import com.rasyidin.notepal.domain.model.detail_note.DetailNote
import com.rasyidin.notepal.domain.model.detail_note.NoteContent
import com.rasyidin.notepal.domain.model.detail_note.Tag
import com.rasyidin.notepal.domain.model.detail_note.Task
import com.rasyidin.notepal.ui.component.Chip
import com.rasyidin.notepal.ui.component.LineSeparator
import com.rasyidin.notepal.ui.component.TextFieldNotes
import com.rasyidin.notepal.ui.component.TextFieldTitleNotes
import com.rasyidin.notepal.ui.component.TileButton
import com.rasyidin.notepal.ui.component.ToolbarNotes
import com.rasyidin.notepal.ui.theme.NotePalTheme
import com.rasyidin.notepal.util.UiText
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailNotesContent(
    modifier: Modifier = Modifier,
    uiState: DetailNote,
    onBackClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onBookmarkClick: (Boolean) -> Unit = {},
    onDeleteClick: () -> Unit = {},
    onMenuSheetClick: (MenuExtra) -> Unit = {},
    onColorNoteClick: (ColorNote) -> Unit = {},
    onAddFreeText: () -> Unit = {},
    onAddChecklist: () -> Unit = {},
    onAddImage: () -> Unit = {},
    onTitleChange: (String) -> Unit = {},
    onContentChange: (List<Content>) -> Unit = {},
) {
    var showSheet by remember { mutableStateOf(uiState.showSheetMenuExtra) }
    var isPinned by remember { mutableStateOf(false) }
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
            LazyColumn(
                modifier = Modifier
                    .weight(1F)
            ) {
                item {
                    Column {
                        Spacer(modifier = Modifier.height(24.dp))
                        TextFieldTitleNotes(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            title = uiState.title,
                            hint = stringResource(id = R.string.title_here),
                            onValueChange = onTitleChange
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                items(uiState.contents) { item ->
                    when (item.type) {
                        ContentType.FreeText -> {
                            TextFieldNotes(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp),
                                text = item.noteContent?.value.orEmpty(),
                                hint = stringResource(id = R.string.hint_notes),
                                onTextChange = { newText ->
                                    uiState.contents.map { content ->
                                        if (content.noteContent?.id == item.noteContent?.id) {
                                            content.noteContent?.value = newText
                                        }
                                    }
                                    onContentChange(uiState.contents)
                                }
                            )
                        }
                        ContentType.Image -> {
                            ImageContent(
                                image = item.noteContent?.value.orEmpty(),
                                onEditClick = {  },
                                onImageClick = { },
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            )
                        }
                        ContentType.Task -> {
                            ItemTask(
                                task = item.task!!,
                                onFinishClick = { isFinish ->
                                    uiState.contents.map { content ->
                                        if (content.task?.id == item.task?.id) {
                                            content.task?.finished = isFinish
                                        }
                                    }
                                    onContentChange(uiState.contents)
                                },
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                onNameChange = { newText ->
                                    uiState.contents.map { content ->
                                        if (content.task?.id == item.task?.id) {
                                            content.task?.name = newText
                                        }
                                    }
                                    onContentChange(uiState.contents)
                                }
                            )
                        }
                    }
                }

                item {
                    Column {
                        Spacer(modifier = Modifier.height(16.dp))
                        LineSeparator(modifier = Modifier.padding(horizontal = 16.dp))

                        // Actions section
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = stringResource(id = R.string.actions).uppercase(),
                            color = MaterialTheme.colorScheme.outline,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TileButton(
                            modifier = Modifier
                                .clickable { onAddFreeText() }
                                .padding(16.dp),
                            menu = MenuExtra(
                                leadingIcon = R.drawable.ic_pencil_alt,
                                title = UiText.StringResource(R.string.add_free_text_area),
                                colors = MenuExtra.Colors(
                                    colorLeadingIcon = MaterialTheme.colorScheme.primary,
                                    colorTitle = MaterialTheme.colorScheme.primary
                                )
                            )
                        )
                        TileButton(
                            modifier = Modifier
                                .clickable { onAddChecklist() }
                                .padding(16.dp),
                            menu = MenuExtra(
                                leadingIcon = R.drawable.ic_check,
                                title = UiText.StringResource(R.string.add_checklist),
                                colors = MenuExtra.Colors(
                                    colorLeadingIcon = MaterialTheme.colorScheme.primary,
                                    colorTitle = MaterialTheme.colorScheme.primary
                                )
                            )
                        )
                        TileButton(
                            modifier = Modifier
                                .clickable { onAddImage() }
                                .padding(16.dp),
                            menu = MenuExtra(
                                leadingIcon = R.drawable.ic_photograph,
                                title = UiText.StringResource(R.string.add_image),
                                colors = MenuExtra.Colors(
                                    colorLeadingIcon = MaterialTheme.colorScheme.primary,
                                    colorTitle = MaterialTheme.colorScheme.primary
                                )
                            )
                        )
                        LineSeparator(modifier = Modifier.padding(horizontal = 16.dp))

                        // Reminder section
                        Spacer(modifier = Modifier.height(24.dp))
                        Reminder(date = "15/07/2021, 18:30")

                        // Label section
                        Spacer(modifier = Modifier.height(24.dp))
                        Labels(
                            labels = uiState.tags,
                            onLabelClick = {

                            }
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }

            // Footer menu
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
                    onClick = {
                        isPinned = !isPinned
                        onBookmarkClick(isPinned)
                    },
                    content = {
                        Icon(
                            painter = if (isPinned) {
                                painterResource(id = R.drawable.ic_bookmark_filled)
                            } else painterResource(id = R.drawable.ic_bookmark_outlined),
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

@Composable
fun ImageContent(
    modifier: Modifier = Modifier,
    image: String,
    onEditClick: () -> Unit,
    onImageClick: (String) -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable { onImageClick(image) },
    ) {
        val (imageContent, btnEdit) = createRefs()
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .constrainAs(imageContent) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onPrimary)
                .clickable { onEditClick() }
                .constrainAs(btnEdit) {
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom, 16.dp)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pencil_outlined),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun ItemTask(
    modifier: Modifier = Modifier,
    task: Task,
    onFinishClick: (Boolean) -> Unit,
    onNameChange: (String) -> Unit
) {
    var isFinished by remember { mutableStateOf(task.finished) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .border(
                    width = 1.dp,
                    color = if (isFinished) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.outline,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .background(if (isFinished) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary)
                .clickable {
                    isFinished = !isFinished
                    onFinishClick(isFinished)
                },
            contentAlignment = Alignment.Center
        ) {
            Column {
                AnimatedVisibility(visible = isFinished) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .size(16.dp)
                            .padding(2.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        TextFieldNotes(
            text = task.name,
            modifier = Modifier.fillMaxWidth(),
            onTextChange = onNameChange,
            hint = stringResource(id = R.string.hint_task),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Labels(
    modifier: Modifier = Modifier,
    labels: List<Tag>,
    onLabelClick: (Tag) -> Unit,
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
                text = label.tag,
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
        DetailNotesContent(
            uiState = DetailNote(
                contents = listOf(
                    Content(),
                    Content(
                        type = ContentType.Image,
                        noteContent = NoteContent(
                            idNote = "001",
                            id = "2",
                            orderContent = 2,
                            value = "https://images.pexels.com/photos/1402787/pexels-photo-1402787.jpeg?cs=srgb&dl=pexels-vlad-alexandru-popa-1402787.jpg&fm=jpg&w=1920&h=1280&_gl=1*1dc3akt*_ga*MTUwMTExMTIzMC4xNjk2Mzg2NzQ5*_ga_8JE65Q40S6*MTY5OTM0MTcyMC4zLjEuMTY5OTM0MTcyNi4wLjAuMA..",
                            type = NoteContentType.Image
                        )
                    ),
                    Content(
                        type = ContentType.Task,
                        task = Task(
                            id = "3",
                            idNote = "001",
                            name = "A box of instant noodles"
                        )
                    ),
                    Content(
                        type = ContentType.Task,
                        task = Task(
                            id = "4",
                            idNote = "001",
                            name = "A box of instant noodles",
                            finished = true
                        )
                    )
                ),
            ),
        )
    }
}