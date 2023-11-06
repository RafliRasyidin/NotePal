package com.rasyidin.notepal.domain.model.detail_note

import com.rasyidin.notepal.data.local.entities.task.TaskEntity

data class Task(
    val id: String = "",
    var idNote: String = "",
    var name: String = "",
    var order: Int = 1,
    var finished: Boolean = false,
    var parentId: String? = null,
) {
    fun toEntity() = TaskEntity(
        id = id,
        idNote = idNote,
        name = name,
        order = order,
        finished = if (finished) 1 else 0,
        parentId = parentId
    )
}
