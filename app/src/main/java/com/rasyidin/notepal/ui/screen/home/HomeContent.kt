package com.rasyidin.notepal.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rasyidin.notepal.R
import com.rasyidin.notepal.domain.model.add_notes.NoteType
import com.rasyidin.notepal.domain.model.add_notes.NoteType.Checklist
import com.rasyidin.notepal.domain.model.add_notes.NoteType.ChecklistWithSub
import com.rasyidin.notepal.domain.model.add_notes.NoteType.FreeNotes
import com.rasyidin.notepal.domain.model.add_notes.NoteType.Goals
import com.rasyidin.notepal.domain.model.add_notes.NoteType.PinnedNote
import com.rasyidin.notepal.domain.model.add_notes.NoteType.Routines
import com.rasyidin.notepal.domain.model.home.Note
import com.rasyidin.notepal.domain.model.home.NoteColor
import com.rasyidin.notepal.ui.component.NegativeState
import com.rasyidin.notepal.ui.theme.NotePalTheme
import com.rasyidin.notepal.ui.theme.notesPurple
import com.rasyidin.notepal.ui.theme.notesPurpleContainer

@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val isEmpty = false
        if (isEmpty) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                NegativeState(
                    modifier = Modifier.align(Alignment.Center),
                    image = R.drawable.illustration_5,
                    title = stringResource(id = R.string.start_your_journey),
                    description = stringResource(id = R.string.start_your_journey_description)
                )
            }
        } else {
            ListNotes()
        }
    }
}

@Composable
fun ListNotes(
    modifier: Modifier = Modifier
) {
    val listNotes = listOf(
        Note(
            idNote = "",
            title = "Title",
            content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            image = "",
            type = FreeNotes,
            colors = NoteColor(
                background = notesPurple,
                container = notesPurpleContainer,
                onContainer = MaterialTheme.colorScheme.onPrimary
            ),
        ),
        Note(
            idNote = "",
            title = "Title",
            content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            image = "",
            type = FreeNotes,
            colors = NoteColor(
                background = notesPurple,
                container = notesPurpleContainer,
                onContainer = MaterialTheme.colorScheme.onPrimary
            ),
        ),
    )
    val listCategoricalNotes = listOf(
        PinnedNote,
        FreeNotes,
    )
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 16.dp)
    ) {
        items(listCategoricalNotes) { type ->
            ItemCategoricalNotes(
                type = type,
                notes = listNotes,
                pinned = type == PinnedNote,
                onItemClick = {},
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun ItemCategoricalNotes(
    modifier: Modifier = Modifier,
    type: NoteType,
    notes: List<Note>,
    pinned: Boolean = false,
    onViewAllClick: (NoteType) -> Unit = {},
    onItemClick: (Note) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = when (type) {
                    FreeNotes -> stringResource(id = R.string.interesting_idea)
                    Checklist -> stringResource(id = R.string.buying_something)
                    Goals -> stringResource(id = R.string.goals)
                    Routines -> stringResource(id = R.string.guidance)
                    ChecklistWithSub -> stringResource(id = R.string.routine_tasks)
                    PinnedNote -> stringResource(id = R.string.pinned_notes)
                },
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = stringResource(id = R.string.view_all),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraSmall)
                    .clickable { onViewAllClick(type) }
                    .padding(4.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(notes) { item ->
                ItemNote(
                    item = item,
                    onClick = onItemClick,
                    modifier = Modifier.padding(end = 24.dp),
                    pinned = pinned
                )
            }
        }
    }
}

@Composable
fun ItemNote(
    modifier: Modifier = Modifier,
    item: Note,
    pinned: Boolean,
    onClick: (Note) -> Unit
) {
    Column(
        modifier = modifier
            .width(180.dp)
            .clip(MaterialTheme.shapes.small)
            .background(item.colors.background)
            .clickable { onClick(item) }
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (!item.image.isNullOrEmpty()) {
            AsyncImage(
                model = item.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(156.dp, 80.dp)
                    .clip(MaterialTheme.shapes.small)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(
            text = item.content,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            overflow = TextOverflow.Ellipsis,
            maxLines = if (item.image.isNullOrEmpty()) 10 else 4,
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, bottom = 16.dp)
        )
        if (pinned) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(item.colors.container)
                    .padding(vertical = 8.dp, horizontal = 12.dp)
            ) {
                Text(
                    text = when (item.type) {
                        FreeNotes -> stringResource(id = R.string.interesting_idea)
                        Checklist -> stringResource(id = R.string.buying_something)
                        Goals -> stringResource(id = R.string.goals)
                        Routines -> stringResource(id = R.string.guidance)
                        ChecklistWithSub -> stringResource(id = R.string.routine_tasks)
                        PinnedNote -> stringResource(id = R.string.pinned_notes)
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = item.colors.onContainer
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewHomeContent() {
    NotePalTheme {
        HomeContent()
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewItemPinnedNote() {
    NotePalTheme {
        ItemNote(
            item = Note(
                idNote = "",
                title = "Title",
                content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                image = "",
                type = FreeNotes,
                colors = NoteColor(
                    background = notesPurple,
                    container = notesPurpleContainer,
                    onContainer = MaterialTheme.colorScheme.onPrimary
                ),
            ),
            onClick = {},
            pinned = true
        )
    }
}

@Preview
@Composable
private fun PreviewItemNote() {
    NotePalTheme {
        ItemNote(
            item = Note(
                idNote = "",
                title = "Title",
                content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                image = "",
                type = FreeNotes,
                colors = NoteColor(
                    background = notesPurple,
                    container = notesPurpleContainer,
                    onContainer = MaterialTheme.colorScheme.onPrimary
                ),
            ),
            onClick = {},
            pinned = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewItemCategoricalNotes() {
    NotePalTheme {
        ItemCategoricalNotes(
            type = FreeNotes,
            notes = listOf(
                Note(
                    idNote = "",
                    title = "Title",
                    content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    image = "",
                    type = FreeNotes,
                    colors = NoteColor(
                        background = notesPurple,
                        container = notesPurpleContainer,
                        onContainer = MaterialTheme.colorScheme.onPrimary
                    ),
                ),
                Note(
                    idNote = "",
                    title = "Title",
                    content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    image = "",
                    type = FreeNotes,
                    colors = NoteColor(
                        background = notesPurple,
                        container = notesPurpleContainer,
                        onContainer = MaterialTheme.colorScheme.onPrimary
                    ),
                ),
                Note(
                    idNote = "",
                    title = "Title",
                    content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    image = "",
                    type = FreeNotes,
                    colors = NoteColor(
                        background = notesPurple,
                        container = notesPurpleContainer,
                        onContainer = MaterialTheme.colorScheme.onPrimary
                    ),
                ),
                Note(
                    idNote = "",
                    title = "Title",
                    content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    image = "",
                    type = FreeNotes,
                    colors = NoteColor(
                        background = notesPurple,
                        container = notesPurpleContainer,
                        onContainer = MaterialTheme.colorScheme.onPrimary
                    ),
                )
            ),
            onItemClick = {}
        )
    }
}