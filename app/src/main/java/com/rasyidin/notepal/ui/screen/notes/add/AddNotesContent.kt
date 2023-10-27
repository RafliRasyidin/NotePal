package com.rasyidin.notepal.ui.screen.notes.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rasyidin.notepal.R
import com.rasyidin.notepal.domain.model.add_notes.AddCardNoteModel
import com.rasyidin.notepal.domain.model.add_notes.CardNoteType
import com.rasyidin.notepal.ui.theme.NotePalTheme
import com.rasyidin.notepal.ui.theme.lightBackground
import com.rasyidin.notepal.ui.theme.notesGreenContainer
import com.rasyidin.notepal.ui.theme.notesGreenDark
import com.rasyidin.notepal.ui.theme.notesPurpleContainer
import com.rasyidin.notepal.ui.theme.notesPurpleDark
import com.rasyidin.notepal.ui.theme.notesRedContainer
import com.rasyidin.notepal.ui.theme.notesRedDark
import com.rasyidin.notepal.ui.theme.notesSalmonContainer
import com.rasyidin.notepal.ui.theme.notesSalmonDark
import com.rasyidin.notepal.ui.theme.notesYellowContainer
import com.rasyidin.notepal.ui.theme.notesYellowDark

@Composable
fun AddNotesContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNoteTypeClick: (AddCardNoteModel) -> Unit
) {
    val addNotesType = listOf(
        AddCardNoteModel(
            title = stringResource(id = R.string.interesting_idea),
            body = stringResource(id = R.string.interesting_idea_body),
            icon = R.drawable.ic_light_bulb_outlined,
            color = notesPurpleContainer,
            colorContainerIcon = notesPurpleDark,
            type = CardNoteType.FreeNotes
        ),
        AddCardNoteModel(
            title = stringResource(id = R.string.buying_something),
            body = stringResource(id = R.string.buying_something_body),
            icon = R.drawable.ic_shopping_cart_outlined,
            color = notesGreenContainer,
            colorContainerIcon = notesGreenDark,
            type = CardNoteType.Checklist
        ),
        AddCardNoteModel(
            title = stringResource(id = R.string.goals),
            body = stringResource(id = R.string.goals_body),
            icon = R.drawable.ic_sparkles_outlined,
            color = notesSalmonContainer,
            colorContainerIcon = notesSalmonDark,
            type = CardNoteType.Goals
        ),
        AddCardNoteModel(
            title = stringResource(id = R.string.guidance),
            body = stringResource(id = R.string.guidance_body),
            icon = R.drawable.ic_clipboard_list_outlined,
            color = notesRedContainer,
            colorContainerIcon = notesRedDark,
            type = CardNoteType.Routines
        ),
        AddCardNoteModel(
            title = stringResource(id = R.string.routine_tasks),
            body = stringResource(id = R.string.routine_tasks_body),
            icon = R.drawable.ic_clipboard_outlined,
            color = notesYellowContainer,
            colorContainerIcon = notesYellowDark,
            type = CardNoteType.ChecklistWithSub
        ),
    )
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            ToolbarAddNotes(
                title = stringResource(id = R.string.new_notes),
                onBackClick = onBackClick
            )
            Spacer(modifier = Modifier.height(16.dp))
            HeadlinePage(
                headline = stringResource(id = R.string.what_do_you_want_to_notes),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ListCardNotesType(
                modifier = Modifier.padding(horizontal = 16.dp),
                items = addNotesType,
                onItemClick = onNoteTypeClick
            )
        }
    }
}

@Composable
private fun ToolbarAddNotes(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable {
                        onBackClick()
                    }
                    .padding(horizontal = 4.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_chevron_left),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.back),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge.copy(textAlign = TextAlign.End),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1F)
            )
            Spacer(modifier = Modifier.weight(1F))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
        )
    }
}

@Composable
private fun HeadlinePage(
    modifier: Modifier = Modifier,
    headline: String
) {
    Text(
        modifier = modifier,
        text = headline,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun ListCardNotesType(
    modifier: Modifier = Modifier,
    items: List<AddCardNoteModel>,
    onItemClick: (AddCardNoteModel) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items) { item ->
            ItemCardNoteType(item = item, onClick = onItemClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ItemCardNoteType(
    modifier: Modifier = Modifier,
    item: AddCardNoteModel,
    onClick: (AddCardNoteModel) -> Unit
) {
    Card(
        modifier = modifier.padding(bottom = 24.dp),
        colors = CardDefaults.cardColors(
            containerColor = item.color
        ),
        shape = MaterialTheme.shapes.small,
        onClick = {
            onClick(item)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(color = item.colorContainerIcon)
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = null,
                    tint = lightBackground
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .weight(1F)
                    .height(IntrinsicSize.Max),
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = lightBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.body,
                    style = MaterialTheme.typography.bodySmall,
                    color = lightBackground
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddNotesContent() {
    NotePalTheme {
        AddNotesContent(
            onBackClick = {},
            onNoteTypeClick = {}
        )
    }
}

@Preview()
@Composable
fun PreviewCardTypeNotes() {
    NotePalTheme {
        ItemCardNoteType(
            item = AddCardNoteModel(
                title = stringResource(id = R.string.interesting_idea),
                body = stringResource(id = R.string.interesting_idea_body),
                icon = R.drawable.ic_light_bulb_outlined,
                color = notesPurpleContainer,
                colorContainerIcon = notesPurpleDark,
                type = CardNoteType.FreeNotes
            ),
            onClick = {}
        )
    }
}