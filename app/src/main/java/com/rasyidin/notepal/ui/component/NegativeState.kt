package com.rasyidin.notepal.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rasyidin.notepal.R
import com.rasyidin.notepal.ui.theme.NotePalTheme

@Composable
fun NegativeState(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    title: String,
    description: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = modifier.size(240.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge.copy(textAlign = TextAlign.Center),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNegativeState() {
    NotePalTheme {
        NegativeState(
            image = R.drawable.illustration_5,
            title = stringResource(id = R.string.start_your_journey),
            description = stringResource(id = R.string.start_your_journey_description)
        )
    }
}

