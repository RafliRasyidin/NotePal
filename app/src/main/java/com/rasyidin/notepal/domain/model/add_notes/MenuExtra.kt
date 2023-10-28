package com.rasyidin.notepal.domain.model.add_notes

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.rasyidin.notepal.R
import com.rasyidin.notepal.util.UiText

data class MenuExtra(
    @DrawableRes val leadingIcon: Int,
    @DrawableRes val trailingIcon: Int? = null,
    val title: UiText,
    val value: UiText? = null,
    val colors: Colors = Colors(),
) {
    data class Colors(
        val colorLeadingIcon: Color = Color.Unspecified,
        val colorTrailingIcon: Color = Color.Unspecified,
        val colorTitle: Color = Color.Unspecified,
        val colorValue: Color = Color.Unspecified
    )
}

val menusExtra = listOf(
    MenuExtra(
        leadingIcon = R.drawable.ic_clock_outlined,
        trailingIcon = R.drawable.ic_cheveron_right,
        title = UiText.StringResource(R.string.set_reminder),
        value = UiText.StringResource(R.string.not_set),
    ),
    MenuExtra(
        leadingIcon = R.drawable.ic_pencil_alt,
        trailingIcon = R.drawable.ic_cheveron_right,
        title = UiText.StringResource(R.string.change_note_type),
        value = UiText.Unspecified
    ),
    MenuExtra(
        leadingIcon = R.drawable.ic_tag_outlined,
        trailingIcon = R.drawable.ic_cheveron_right,
        title = UiText.StringResource(R.string.add_label),
        value = UiText.StringResource(R.string.not_set)
    ),
    MenuExtra(
        leadingIcon = R.drawable.ic_check,
        trailingIcon = null,
        title = UiText.StringResource(R.string.mark_as_finished),
        value = null
    ),
)
