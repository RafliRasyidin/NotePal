package com.rasyidin.notepal.ui.screen.notes.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rasyidin.notepal.R
import com.rasyidin.notepal.domain.model.add_notes.ColorNote
import com.rasyidin.notepal.domain.model.add_notes.MenuExtra
import com.rasyidin.notepal.domain.model.add_notes.NoteType
import com.rasyidin.notepal.domain.model.add_notes.NoteType.Checklist
import com.rasyidin.notepal.domain.model.add_notes.NoteType.ChecklistWithSub
import com.rasyidin.notepal.domain.model.add_notes.NoteType.FreeNotes
import com.rasyidin.notepal.domain.model.add_notes.NoteType.Goals
import com.rasyidin.notepal.domain.model.add_notes.NoteType.PinnedNote
import com.rasyidin.notepal.domain.model.add_notes.NoteType.Routines
import com.rasyidin.notepal.domain.model.add_notes.colorsNote
import com.rasyidin.notepal.domain.model.add_notes.menusExtra
import com.rasyidin.notepal.ui.component.LineSeparator
import com.rasyidin.notepal.ui.theme.NotePalTheme
import com.rasyidin.notepal.util.UiText

@Composable
fun DragHandleSheetExtrasMenuNote(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(end = 16.dp, top = 16.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .size(24.dp)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_x),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(2.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun SheetExtrasMenuNoteContent(
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit = {},
    onMenuClick: (MenuExtra) -> Unit = {},
    onColorClick: (ColorNote) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.change_background).uppercase(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(16.dp))
        PickColors(
            colors = colorsNote,
            onItemClick = onColorClick
        )
        Spacer(modifier = Modifier.height(16.dp))
        LineSeparator()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.extras).uppercase(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        MenuExtras(
            menus = menusExtra,
            noteType = FreeNotes,
            onMenuClick = onMenuClick
        )
        Spacer(modifier = Modifier.height(8.dp))
        LineSeparator()
        Spacer(modifier = Modifier.height(8.dp))
        TileButton(
            menu = MenuExtra(
                leadingIcon = R.drawable.ic_trash,
                title = UiText.StringResource(R.string.delete_note),
                colors = MenuExtra.Colors(
                    colorLeadingIcon = MaterialTheme.colorScheme.error,
                    colorTitle = MaterialTheme.colorScheme.error
                )
            ),
            onClick = { onDeleteClick() }
        )
    }
}

@Composable
private fun MenuExtras(
    modifier: Modifier = Modifier,
    menus: List<MenuExtra>,
    noteType: NoteType,
    onMenuClick: (MenuExtra) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(menus) { menu ->
            TileButton(
                menu = menu.copy(
                    value = if (menu.value == UiText.Unspecified) {
                        when (noteType) {
                            FreeNotes -> UiText.StringResource(R.string.interesting_idea)
                            Checklist -> UiText.StringResource(R.string.buying_something)
                            Goals -> UiText.StringResource(R.string.goals)
                            Routines -> UiText.StringResource(R.string.guidance)
                            ChecklistWithSub -> UiText.StringResource(R.string.routine_tasks)
                            PinnedNote -> UiText.StringResource(R.string.pinned_notes)
                        }
                    } else menu.value
                ),
                onClick = onMenuClick,
            )
        }
    }
}

@Composable
private fun TileButton(
    modifier: Modifier = Modifier,
    menu: MenuExtra,
    onClick: (MenuExtra) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(menu) }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(2F)
        ) {
            Icon(
                painter = painterResource(id = menu.leadingIcon),
                contentDescription = null,
                tint = if (menu.colors.colorLeadingIcon == Color.Unspecified) {
                    MaterialTheme.colorScheme.onBackground
                } else {
                    menu.colors.colorLeadingIcon
                },
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = menu.title.asString(),
                style = MaterialTheme.typography.titleMedium,
                color = if (menu.colors.colorTitle == Color.Unspecified) {
                    MaterialTheme.colorScheme.onBackground
                } else menu.colors.colorTitle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        if (menu.trailingIcon != null && menu.value != null) {
            Row(
                modifier = Modifier.weight(1F),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = menu.value.asString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (menu.colors.colorValue == Color.Unspecified) {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    } else menu.colors.colorValue,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = menu.trailingIcon),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = if (menu.colors.colorTrailingIcon == Color.Unspecified) {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    } else menu.colors.colorTrailingIcon
                )
            }
        }
    }
}

@Composable
private fun PickColors(
    modifier: Modifier = Modifier,
    colors: List<ColorNote>,
    onItemClick: (ColorNote) -> Unit,
) {
    LazyRow(modifier = modifier) {
        items(colors) { item ->
            PickColorNote(
                colorNote = item,
                selected = true,
                onItemClick = onItemClick,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

@Composable
fun PickColorNote(
    modifier: Modifier = Modifier,
    colorNote: ColorNote,
    selected: Boolean,
    onItemClick: (ColorNote) -> Unit,
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(colorNote.color)
            .clickable { onItemClick(colorNote) }
            .then(
                if (selected) {
                    Modifier
                        .border(
                            border = BorderStroke(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            shape = CircleShape
                        )
                        .padding(4.dp)
                        .border(
                            border = BorderStroke(
                                width = 0.5.dp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            shape = CircleShape
                        )
                } else Modifier
            )
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewSheetExtrasMenuNoteContent() {
    NotePalTheme {
        SheetExtrasMenuNoteContent()
    }
}