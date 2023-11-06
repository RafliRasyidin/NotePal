package com.rasyidin.notepal.data.local.entities.tag

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag")
data class TagEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("idNote")
    val idNote: String,

    @ColumnInfo("tag")
    val tag: String,
)
