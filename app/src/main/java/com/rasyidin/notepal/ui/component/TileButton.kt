package com.rasyidin.notepal.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rasyidin.notepal.domain.model.add_notes.MenuExtra

@Composable
fun TileButton(
    modifier: Modifier = Modifier,
    menu: MenuExtra,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
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