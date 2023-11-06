package com.rasyidin.notepal.data.local.entities.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("updatedAt")
    val updatedAt: String,

    @ColumnInfo("createdAt")
    val createdAt: String,

    @ColumnInfo("reminder")
    val reminder: String,

    @ColumnInfo("pinned")
    val pinned: Int,

    @ColumnInfo("finished")
    val finished: Int,

    @ColumnInfo("backgroundColor")
    val backgroundColor: Int,

    @ColumnInfo("containerColor")
    val containerColor: Int,

    @ColumnInfo("onContainerColor")
    val onContainerColor: Int
)
