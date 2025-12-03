package com.proyek.planity

data class Task(
    val title: String,
    val description: String,
    val time: String? = null,
    var isCompleted: Boolean = false

)
