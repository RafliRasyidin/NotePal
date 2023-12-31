package com.rasyidin.notepal.domain.model.add_notes

import androidx.compose.ui.graphics.Color
import com.rasyidin.notepal.ui.theme.notesGreen
import com.rasyidin.notepal.ui.theme.notesPurple
import com.rasyidin.notepal.ui.theme.notesRed
import com.rasyidin.notepal.ui.theme.notesSalmon
import com.rasyidin.notepal.ui.theme.notesWhite
import com.rasyidin.notepal.ui.theme.notesYellow

data class ColorNote(
    val id: String,
    var color: Color
)

val colorsNote = listOf(
    ColorNote(
        id = "1",
        color = notesWhite
    ),
    ColorNote(
        id = "2",
        color = notesRed
    ),
    ColorNote(
        id = "3",
        color = notesPurple
    ),
    ColorNote(
        id = "4",
        color = notesGreen
    ),
    ColorNote(
        id = "5",
        color = notesYellow
    ),
    ColorNote(
        id = "6",
        color = notesSalmon
    ),
)

const val ColorIdWhite = "1"
const val ColorIdRed = "2"
const val ColorIdPurple = "3"
const val ColorIdGreen = "4"
const val ColorIdYellow = "5"
const val ColorIdSalmon = "6"
