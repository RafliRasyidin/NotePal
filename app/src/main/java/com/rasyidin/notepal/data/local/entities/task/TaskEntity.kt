package com.rasyidin.notepal.data.local.entities.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("idNote")
    val idNote: String,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("order")
    val order: Int,

    @ColumnInfo("finished")
    val finished: Int = 0,

    @ColumnInfo("parentId")
    val parentId: String? = null,
)
