package com.rasyidin.notepal.domain.model.add_notes

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class AddCardNoteModel(
    val title: String,
    val body: String,
    @DrawableRes val icon: Int,
    val type: CardNoteType,
    val color: Color,
    val colorContainerIcon: Color,
)

enum class CardNoteType {
    FreeNotes,
    Checklist,
    Goals,
    Routines,
    ChecklistWithSub
}
