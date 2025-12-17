package com.proyek.planity

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(), //menambahkan id secara otomatis saat membuat tugas, bertipe data string
    val title: String,
    val description: String,
    val time: String? = null,
    var isCompleted: Boolean = false

)
