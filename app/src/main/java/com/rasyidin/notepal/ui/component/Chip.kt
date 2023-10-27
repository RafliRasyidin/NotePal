package com.rasyidin.notepal.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rasyidin.notepal.R
import com.rasyidin.notepal.ui.theme.NotePalTheme
import com.rasyidin.notepal.util.clickableIcon

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    showDeleteButton: Boolean = false,
    onDeleteClick: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium
            )
            AnimatedVisibility(visible = showDeleteButton) {
                Icon(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.outline,
                            shape = CircleShape
                        )
                        .size(16.dp)
                        .padding(2.dp)
                        .clickableIcon {
                            onDeleteClick()
                        },
                    painter = painterResource(id = R.drawable.ic_x),
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewChip() {
    NotePalTheme {
        Chip(text = "Important")
    }
}

@Preview
@Composable
private fun PreviewChip2() {
    NotePalTheme {
        Chip(
            text = "Important",
            showDeleteButton = true
        )
    }
}