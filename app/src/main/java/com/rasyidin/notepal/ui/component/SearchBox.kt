package com.rasyidin.notepal.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rasyidin.notepal.R
import com.rasyidin.notepal.ui.theme.NotePalTheme
import com.rasyidin.notepal.util.clickableIcon

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    query: String,
    hint: String = "",
    shape: Shape = MaterialTheme.shapes.small,
    enabled: Boolean = true,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit
) {
    var text by remember { mutableStateOf(query) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = shape
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier
                .weight(1F)
                .onFocusChanged { isFocus ->
                    onFocusChange(isFocus.isFocused)
                },
            value = query,
            onValueChange = { newText ->
                text = newText
                onQueryChange(text)
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                selectionColors = TextSelectionColors(
                    handleColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer
                )
            ),
            textStyle = MaterialTheme.typography.labelMedium,
            enabled = enabled,
            singleLine = true,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            },
            trailingIcon = {
                AnimatedVisibility(visible = query.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = CircleShape
                            )
                            .clickableIcon {
                                text = ""
                                onQueryChange(text)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_x),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(text)
                }
            )
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun PreviewSearchBar() {
    NotePalTheme {
        SearchBox(
            query = "123",
            hint = "Search...",
            onQueryChange = {

            },
            onSearch = {

            },
            onFocusChange = {

            }
        )
    }
}