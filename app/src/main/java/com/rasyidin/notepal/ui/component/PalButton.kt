package com.rasyidin.notepal.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rasyidin.notepal.R
import com.rasyidin.notepal.domain.model.component.ButtonStyles
import com.rasyidin.notepal.domain.model.component.ButtonStyles.Outlined
import com.rasyidin.notepal.domain.model.component.ButtonStyles.Primary
import com.rasyidin.notepal.domain.model.component.ButtonStyles.Secondary
import com.rasyidin.notepal.ui.theme.NotePalTheme

@Composable
fun PalButton(
    modifier: Modifier = Modifier,
    styles: ButtonStyles = Primary,
    text: String = "",
    iconEnd: Int? = null,
    iconStart: Int? = null,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(100.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        contentPadding = contentPadding,
        colors = when (styles) {
            Primary -> ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
            )
            Secondary -> ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
            )
            Outlined -> ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        },
        border = if (styles == Outlined)  {
            BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary)
        } else null,
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (iconStart != null) {
                Icon(
                    painter = painterResource(id = iconStart),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                modifier = Modifier
                    .weight(1F)
                    .then(
                        when {
                            iconStart == null && iconEnd != null -> Modifier.padding(start = 20.dp)
                            iconStart != null && iconEnd == null -> Modifier.padding(end = 20.dp)
                            else -> Modifier
                        }
                    ),
                text = text,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
            if (iconEnd != null) {
                Icon(
                    painter = painterResource(id = iconEnd),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPalButtonPrimary() {
    NotePalTheme {
        PalButton(
            modifier = Modifier.fillMaxWidth(),
            styles = Primary,
            text = "Button",
            iconEnd = R.drawable.ic_arrow_right
        ) {

        }
    }
}

@Preview
@Composable
private fun PreviewPalButtonSecondary() {
    NotePalTheme {
        PalButton(
            modifier = Modifier.fillMaxWidth(),
            styles = Secondary,
            text = "Button",
        ) {

        }
    }
}

@Preview
@Composable
private fun PreviewPalButtonOutlined() {
    NotePalTheme {
        PalButton(
            modifier = Modifier.fillMaxWidth(),
            styles = Outlined,
            text = "Button",
            iconStart = R.drawable.ic_arrow_left,
        ) {

        }
    }
}