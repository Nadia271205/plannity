package com.proyek.planity

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val time: String? = null,
    var isCompleted: Boolean = false
)
