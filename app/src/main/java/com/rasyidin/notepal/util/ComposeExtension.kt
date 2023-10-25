package com.rasyidin.notepal.util

import android.annotation.SuppressLint
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@SuppressLint("UnnecessaryComposedModifier", "ComposableModifierFactory")
@Composable
fun Modifier.clickableIcon(
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication = rememberRipple(bounded = false),
    onClick: () -> Unit
) = composed {
    clickable(
        interactionSource = interactionSource,
        indication = indication
    ) {
        onClick()
    }
}